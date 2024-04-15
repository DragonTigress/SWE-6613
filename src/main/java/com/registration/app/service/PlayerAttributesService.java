package com.registration.app.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration.app.entity.Attribute;
import com.registration.app.entity.Registration;
import com.registration.app.repository.PlayerAttributesRepository;
import com.registration.app.repository.RegistrationRepository;
import com.registration.app.response.Profile;

@Service
public class PlayerAttributesService {
	
	@Autowired
	PlayerAttributesRepository playerAttributesRepository;
	
	@Autowired
	RegistrationRepository registrationRepository;
	
	@Autowired
	ObjectMapper objectMapper;

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

	public String findMatches(String emailAddress) {
		String response = null;
		if(!emailAddress.isBlank()){
			
			Registration playerRecord = registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress);
			
			if(ObjectUtils.isEmpty(playerRecord)) {
				return "Player is not registered into the system " + emailAddress;
		
			}else {
				Attribute playerAttribute	= playerAttributesRepository.readPlayerAttributeRecordsByEmailAddress(emailAddress);
				if(ObjectUtils.isEmpty(playerAttribute)) {
					return "To find the matching profiles, please provide your preferred attributes first ";
				}else {
				
					List<Attribute> allPlayersAttributes = (List<Attribute>) playerAttributesRepository.findAll();
					
					List<Profile> matchedProfiles = findMatchingProfiles(playerAttribute, allPlayersAttributes );
					
					try {
						if(matchedProfiles.size() == 0) {
							return "No Matches Found";
							
						}
						response = objectMapper.writeValueAsString(matchedProfiles); 
						
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
		}else {
			return "Email Address is required";
		}		
		
		return response;
	}

	private List<Profile> findMatchingProfiles(Attribute playerAttribute,
			List<Attribute> allPlayersAttributes) {
		
		
		List<Profile> profiles = new ArrayList<>();
		
		for(int i = 0 ; i < allPlayersAttributes.size(); i++) {
				
			Attribute attribute = allPlayersAttributes.get(i);
			
			if(!attribute.getEmailAddress().equals(playerAttribute.getEmailAddress())) {
				
				Registration playerRecord = registrationRepository.readRegistrationRecordsByEmailAddress(attribute.getEmailAddress());
			
				Profile profile = new Profile();
				String[] ageRangeArray = attribute.getAgeRange().split("-"); 
			
							
				if(playerAttribute.getGender().equalsIgnoreCase("both") || playerRecord.getGender().equals(playerAttribute.getGender())) {
					profile.setGender(playerRecord.getGender());	
				}
				
				if(attribute.getMicroPhone().equals(playerAttribute.getMicroPhone())){
					profile.setMicroPhone(attribute.getMicroPhone());
				}
				
				if(playerAttribute.getNintendoSwitch().equalsIgnoreCase("yes") && attribute.getNintendoSwitch().equalsIgnoreCase("yes")) {
					profile.getConsolePreferences().add("nintendoSwitch");
				}
				if(playerAttribute.getPc().equalsIgnoreCase("yes") && attribute.getPc().equalsIgnoreCase("yes")) {
					profile.getConsolePreferences().add("pc");
				}
				if(playerAttribute.getPlayStation().equalsIgnoreCase("yes") && attribute.getPlayStation().equalsIgnoreCase("yes")) {
					profile.getConsolePreferences().add("playStation");
				}
				if(playerAttribute.getxBox().equalsIgnoreCase("yes") && attribute.getxBox().equalsIgnoreCase("yes")) {
					profile.getConsolePreferences().add("xBox");
				}
				
				if( (Integer.parseInt(playerRecord.getAge()) >= Integer.parseInt(ageRangeArray[0])) &&  (Integer.parseInt(playerRecord.getAge()) <= Integer.parseInt(ageRangeArray[1]))) {
					profile.setAge(playerRecord.getAge());
				}
				
				if(profile.getGender() != null  && profile.getMicroPhone() != null && profile.getConsolePreferences().size() > 0 && profile.getAge() != null) {
					
					profile.setFirstName(playerRecord.getFirstName());
					profile.setLastName(playerRecord.getLastName());
					profile.setTimeZone(attribute.getTimeZone());
					profiles.add(profile);
					
				}		
				
			}else {
				
			}
			
		}
		return profiles;
	}

}
