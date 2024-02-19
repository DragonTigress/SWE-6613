package com.registration.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.registration.app.entity.Registration;
import com.registration.app.repository.RegistrationRepository;

@Service
public class RegistrationService {
	
	@Autowired
	RegistrationRepository registrationRepository;

	public String register(String firstName, String lastName, String contactNumber, String emailAddress, String password,
			String confirmPassword) {
		
		
		Registration duplicateRecord = registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress);
		
		if(ObjectUtils.isEmpty(duplicateRecord)) {
			
			if(!emailAddress.contains("@")){
				return "Invalid Email Address";
				
			}
			if(!password.equals(confirmPassword)){
				return "password & confirm password doesn't match";
			}
			
			Registration registration = new Registration(firstName, lastName, contactNumber, emailAddress, password);
			registrationRepository.save(registration);		
			
		}else {
					
			return "This user is already registered " + duplicateRecord.getEmailAddress();
		}
		
		return "User Registration successful";
	}
	

}
