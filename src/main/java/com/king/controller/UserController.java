package com.king.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.king.dto.QuoteRespondDTO;
import com.king.dto.ResetPwdDTO;
import com.king.dto.UserDTO;
import com.king.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(Model model) {
		UserDTO userDto= new UserDTO();
		model.addAttribute("user",userDto);
		return "index";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") UserDTO user,Model model) {
		
		UserDTO login = userService.login(user.getEmail(), user.getPwd());
		
		//if login credential is wrong
		if(login==null) {
			model.addAttribute("emsg","Invalid Credentials");
			return "index";
		}
		
		if(login.getPwdUpdate().equals("YES")) {
			//display dashboard page
			QuoteRespondDTO quotation = userService.getQuotation();
			model.addAttribute("quote",quotation);
			return "dashboard";
		}
		else {
				//display reset password page
			ResetPwdDTO resetPwd= new ResetPwdDTO();
			resetPwd.setEmail(login.getEmail());
			model.addAttribute("resetPwd",resetPwd);
			return "resetPwd";
		}
	}
	
	
	@GetMapping("/register")
	public String register(Model model) {
		
		UserDTO userDto = new UserDTO();
		model.addAttribute("user", userDto);
		
		Map<Integer, String> countriesMap = userService.getCountries();
		model.addAttribute("countries", countriesMap);
		
		return "register";
	}
	
	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer,String> getStates(@PathVariable Integer countryId){
		return userService.getStates(countryId);
	}
	
	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer,String> getCities(@PathVariable Integer stateId){
		return userService.getCities(stateId);
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") UserDTO user, Model model) {
		boolean uniqueEmail = userService.isUniqueEmail(user.getEmail());
		if(uniqueEmail) {
			boolean register = userService.register(user);
			if(register) {
				model.addAttribute("smsg","Registration Successful");
			}
			else {
				model.addAttribute("emsg", "Registation Failed");
			}
		}
		else {
			model.addAttribute("emsg", "Duplicate Email Found");
		}
		Map<Integer, String> countriesMap = userService.getCountries();
		model.addAttribute("countries", countriesMap);
		
		return "register";
	}
	
	@PostMapping("/resetPwd")
	public String resetPwd(@ModelAttribute("resetPwd") ResetPwdDTO resetPwd ,Model model) {
		
		if(resetPwd.getNewPwd().equals(resetPwd.getConfirmPwd())) {
			userService.resetPwd(resetPwd);
			QuoteRespondDTO quotation= userService.getQuotation();
			model.addAttribute("quote",quotation);
			return "dashboard";
			
		}else {
			model.addAttribute("emsg", "New Password and Confirm password is not matching");
			return "resetPwd";
		}
	}
		@GetMapping("/getQuote")
		public String getQuote(Model model) {
			QuoteRespondDTO quotation = userService.getQuotation();
			model.addAttribute("quote", quotation);
			return "dashboard";
		}
	
	
	
}
