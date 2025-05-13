package com.king.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.king.dto.QuoteRespondDTO;
import com.king.dto.ResetPwdDTO;
import com.king.dto.UserDTO;
import com.king.entity.CityEntity;
import com.king.entity.CountryEntity;
import com.king.entity.StateEntity;
import com.king.entity.UserEntity;
import com.king.repository.CityRepo;
import com.king.repository.CountryRepo;
import com.king.repository.StateRepo;
import com.king.repository.UserRepo;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CountryRepo countryRepo;
	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private CityRepo cityRepo;
	@Autowired
	private EmailService emailService;

	@Override
	public UserDTO login(String email, String pwd) {

		UserEntity entity = userRepo.findByEmailAndPwd(email, pwd);
		// copy entity object data to dto object data for showing data in form
		if (entity != null) {
			UserDTO dto = new UserDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return null;
	}

	@Override
	public Map<Integer, String> getCountries() {

		List<CountryEntity> list = countryRepo.findAll();

		Map<Integer, String> countryMap = new HashMap<Integer, String>();

		list.forEach(country -> {
			countryMap.put(country.getCountryId(), country.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {

		List<StateEntity> list = stateRepo.findByCountryCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<Integer, String>();
		list.forEach(states -> {
			stateMap.put(states.getStateId(), states.getStateName());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CityEntity> list = cityRepo.findByStateStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		list.forEach(cities -> {
			cityMap.put(cities.getCityId(), cities.getCityName());
		});

		return cityMap;
	}

	@Override
	public boolean isUniqueEmail(String email) {
		UserEntity entity = userRepo.findByEmail(email);
		return entity == null;
	}

	@Override
	public boolean register(UserDTO userDTO) {

		String randomPwd = getRandomPwd();
		userDTO.setPwd(randomPwd);
		userDTO.setPwdUpdate("NO");

		// Binding data object are copy to entity object
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(userDTO, entity);
		// by using above country,state,city data will not copy due to object data

		CountryEntity country = countryRepo.findById(userDTO.getCountryId()).get();
		entity.setCountry(country);

		StateEntity state = stateRepo.findById(userDTO.getStateId()).get();
		entity.setState(state);

		CityEntity city = cityRepo.findById(userDTO.getCityId()).get();
		entity.setCity(city);

		UserEntity savedUser = userRepo.save(entity);

		if (savedUser != null) {
			String subject = "Your registattion is success";
			String body = "Your access password is : " + randomPwd;
			return emailService.sendEmail(userDTO.getEmail(), subject, body);
		}
		return false;
	}

	@Override
	public boolean resetPwd(ResetPwdDTO resetPwdDTO) {

		UserEntity entity = userRepo.findByEmail(resetPwdDTO.getEmail());
		entity.setPwd(resetPwdDTO.getNewPwd());
		entity.setPwdUpdate("YES");

		UserEntity save = userRepo.save(entity);
		return save != null;

	}

	@Override
	public QuoteRespondDTO getQuotation() {

		String apiUrl = "https://dummyjson.com/quotes/random";
		RestTemplate rt = new RestTemplate();
		ResponseEntity<QuoteRespondDTO> forEntity = rt.getForEntity(apiUrl, QuoteRespondDTO.class);
		return forEntity.getBody();
	}

	private String getRandomPwd() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		StringBuilder pwd = new StringBuilder();
		Random rnd = new Random();
		while (pwd.length() < 5) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			pwd.append(SALTCHARS.charAt(index));
		}
		return pwd.toString();
	}

}
