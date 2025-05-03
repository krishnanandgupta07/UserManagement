package com.king.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String login(@ModelAttribute UserDTO user,Model model) {
		
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
}
