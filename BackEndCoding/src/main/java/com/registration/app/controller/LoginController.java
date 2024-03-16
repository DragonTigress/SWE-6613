package com.registration.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.registration.app.service.LoginService;


@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/login")
	public String login(
		@RequestParam("emailAddress") String emailAddress,
		@RequestParam("password") String password){
		
		String response = loginService.login(emailAddress,password);
		
		return response;
	
	}
	
	@GetMapping("/checkStatus/{emailAddress}")
	public String checkStatus(
		@PathVariable("emailAddress") String emailAddress){
		
		String response = loginService.checkStatus(emailAddress);
		
		return response;
	
	}
	
	@PostMapping("/logoutUser/{emailAddress}")
	public String logoutUser(
		@PathVariable("emailAddress") String emailAddress){
		
		String response = loginService.logoutUser(emailAddress);
		
		return response;
	
	}

	
	

}
