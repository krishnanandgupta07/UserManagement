package com.king.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.king.dto.QuoteRespondDTO;
import com.king.dto.ResetPwdDTO;
import com.king.dto.UserDTO;
import com.king.entity.CountryEntity;
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

	@Override
	public UserDTO login(String email, String pwd) {
		
		UserEntity entity= userRepo.findByEmailAndPwd(email, pwd);
		//copy entity object data to dto object data for showing data in form
		if(entity!=null) {
			UserDTO dto=new UserDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return null;
	}

	@Override
	public Map<Integer, String> getCountries() {
		
		return null;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUniqueEmail(String email) {
		UserEntity entity= userRepo.findByEmail(email);
		return entity==null;
	}

	@Override
	public boolean register(UserDTO userDTO) {
		
		return false;
	}

	@Override
	public boolean resetPwd(ResetPwdDTO resetPwdDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public QuoteRespondDTO getQuotation() {
		// TODO Auto-generated method stub
		return null;
	}

}
