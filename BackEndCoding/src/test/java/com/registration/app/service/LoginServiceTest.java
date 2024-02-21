package com.registration.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.registration.app.RegistrationApplication;
import com.registration.app.entity.Registration;
import com.registration.app.repository.LoginRepository;
import com.registration.app.repository.RegistrationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RegistrationApplication.class)
public class LoginServiceTest {
	
	@InjectMocks
	private LoginService loginService;
	
	@Mock
	RegistrationRepository registrationRepository;
	
	@Mock
	LoginRepository loginRepository;
	
	@DisplayName("test login method with empty password")
	@Test
	public void testLoginEmptyPassword() {
		
		String emailAddress = "abc@gmail.com";
		String password = "";
		String response = loginService.login(emailAddress, password);
		assertEquals(response, "Email Address and password cannnot be empty");
		
	}
	
	@DisplayName("test login method with empty email address")
	@Test
	public void testLoginEmptyContactNumber() {
		
		String emailAddress = "";
		String password = "123456";
		String response = loginService.login(emailAddress, password);
		assertEquals(response, "Email Address and password cannnot be empty");	
	}
	
	@DisplayName("test login method when user is not registered")
	@Test
	public void testLoginUnregisteredUser() {
		
		String emailAddress = "1234567890";
		String password = "123456";
		Registration registeredUser = null;
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(registeredUser);
		String response = loginService.login(emailAddress, password);
		assertEquals(response, "User is not registered..Please register");	
	}
	
	@DisplayName("test login method when user's credentials are not valid")
	@Test
	public void testLoginInvalidCredentials() {
		
		String emailAddress = "abc@gmail.com";
		String password = "123456";
		
		Registration registeredUser = new Registration("testUser", "testPwd", "12345678", "abc@gmail.com", "testPwd");
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(registeredUser);
		String response = loginService.login(emailAddress, password);
		assertEquals(response, "Invalid Credentials");		
	}
	
	@DisplayName("test login method successfully")
	@Test
	public void testLoginSuccessful() {
		
		String emailAddress = "abc@gmail.com";
		String password = "123456";
		
		Registration registeredUser = new Registration("testUser", "testPwd", "1234567890", "abc@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(registeredUser);
		String response = loginService.login(emailAddress, password);
		assertEquals(response, "Login Successful");	
	}



}
