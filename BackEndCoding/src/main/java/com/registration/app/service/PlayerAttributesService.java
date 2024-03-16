package com.registration.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registration.app.entity.Attribute;
import com.registration.app.repository.PlayerAttributesRepository;

@Service
public class PlayerAttributesService {
	
	@Autowired
	PlayerAttributesRepository playerAttributesRepository;

	public String storeAttributes(String emailAddress, String timeZone, String microPhone, String ageRange, String gender,
			List<String> consolePreference) {
		
		
		Attribute attribute = new Attribute();
		String consoleType = null;
		
		if(emailAddress.isBlank()) {
			return "email address cannot be empty";
		}else {
			attribute.setEmailAddress(emailAddress);
		}
		if(timeZone.isBlank()) {
			return "timeZone cannot be empty";
		}else {
			attribute.setTimeZone(timeZone);
		}
		
		if(microPhone.isBlank()) {
			return "microPhone cannot be empty";
		}else {
			attribute.setMicroPhone(microPhone);
		}
		
		if(ageRange.isBlank()) {
			return "ageRange cannot be empty";
		}else {
			attribute.setAgeRange(ageRange);
		}
		
		if(gender.isBlank()) {
			return "gender cannot be empty";
		}else {
			attribute.setGender(gender);
		}
		
		if(consolePreference.isEmpty()) {
			return "consolePreference cannot be empty";
		}else {
			
			for(int i = 0; i < consolePreference.size(); i++) {
				
				consoleType = consolePreference.get(i);
				if(consoleType.equalsIgnoreCase("pc")) {
					attribute.setPc("Yes");
				}else if(consoleType.equalsIgnoreCase("xBox")) {
					attribute.setxBox("Yes");
				}else if(consoleType.equalsIgnoreCase("playStation")) {
					attribute.setPlayStation("Yes");
				}else if(consoleType.equalsIgnoreCase("nintendoSwitch"))  {
					attribute.setNintendoSwitch("Yes");
				}else {
				
				}
				
			}
		}

		playerAttributesRepository.save(attribute);
		
		return "Player attributes are successfully stored into the system";
	}
	

}
