package com.registration.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.registration.app.service.RegistrationService;

@RestController
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	@PostMapping("/register")
	public String register(
		@RequestParam("firstName") String firstName,
		@RequestParam("lastName") String lastName,
		@RequestParam("contactNumber") String contactNumber,
		@RequestParam("emailAddress") String emailAddress,
		@RequestParam("password") String password,
		@RequestParam("confirmPassword") String confirmPassword) {
		
		String response = registrationService.register(firstName, lastName, contactNumber, emailAddress, password, confirmPassword);
		
		return response;
	
	
	}

}
