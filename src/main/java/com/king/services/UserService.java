package com.king.services;

import java.util.Map;

import com.king.dto.QuoteRespondDTO;
import com.king.dto.ResetPwdDTO;
import com.king.dto.UserDTO;

public interface UserService {

	public UserDTO login(String email,String pwd);
	public Map<Integer,String> getCountries();
	public Map<Integer,String >getStates(Integer countryId);
	public Map<Integer,String>getCities(Integer stateId);
	public boolean isUniqueEmail(String email);
	public boolean register(UserDTO userDTO);
	public boolean resetPwd(ResetPwdDTO resetPwdDTO);
	public QuoteRespondDTO getQuotation();
}
