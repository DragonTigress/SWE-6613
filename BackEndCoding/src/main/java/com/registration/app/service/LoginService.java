package com.registration.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.registration.app.entity.Login;
import com.registration.app.entity.Registration;
import com.registration.app.repository.LoginRepository;
import com.registration.app.repository.RegistrationRepository;

@Service
public class LoginService {
	
	@Autowired
	RegistrationRepository registrationRepository;
	
	@Autowired
	LoginRepository loginRepository; 


	public String login(String emailAddress, String password) {
		
		if(emailAddress.isEmpty() || password.isEmpty()) {
			return "Email Address and password cannnot be empty";
		}else {
			
			Registration registeredUser = registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress);
			
			if(ObjectUtils.isEmpty(registeredUser)) {
				return "User is not registered..Please register";
			}else {
				
				if(registeredUser.getEmailAddress().equals(emailAddress) && registeredUser.getPassword().equals(password)) {
					Login login = new Login(emailAddress,"ACTIVE");
					loginRepository.save(login);
					return "Login Successful";
				}else {
					return "Invalid Credentials";
				}
				
			}			
		}
	}


	public String checkStatus(String emailAddress) {

		if(emailAddress.isEmpty()) {
			return "Email Address cannot be empty";
		}else {
			Login login = loginRepository.checkStatusByEmailAddress(emailAddress);
			if(ObjectUtils.isEmpty(login)) {
				return "Record doesn't exist";
			}else {
				return login.getStatus();
			}
		}
	
	}


	public String logoutUser(String emailAddress) {
		if(emailAddress.isEmpty()) {
			return "Email Address cannot be empty";
		}else {
			loginRepository.logoutUserByEmailAddress(emailAddress);
			return "User logged out";
		}
		
	}
		

}
