package com.registration.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.registration.app.service.PlayerAttributesService;

@RestController
public class PlayerAttributesController {
	
	@Autowired
	PlayerAttributesService playerAttributesService;
	
	@PostMapping("/storeAttributes")
	public String storeAttributes(
			@RequestParam("emailAddress") String emailAddress,
			@RequestParam("timeZone") String timeZone,
			@RequestParam("microPhone") String microPhone,
			@RequestParam("ageRange") String ageRange,
			@RequestParam("gender") String gender,
			@RequestParam("consolePreference") List<String> consolePreference){
		
		String response = playerAttributesService.storeAttributes(emailAddress,timeZone,microPhone,ageRange,gender,consolePreference);
		
		return response;
		
	}

}
