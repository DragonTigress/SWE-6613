package com.registration.app.service;

import static org.junit.Assert.assertTrue;
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
import com.registration.app.repository.RegistrationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RegistrationApplication.class)
public class RegistrationServiceTest {

	@InjectMocks
	RegistrationService registrationService;
	
	@Mock
	RegistrationRepository registrationRepository;

	@DisplayName("test registration method with duplicate user")
	@Test
	public void testRegistrationDuplicateUser() {
		
		String firstName = "TestFirstName";
		String lastName = "TestLastName";
		String contactNumber = "1234567890";
		String emailAddress = "abc@gmail.com";
		String password = "123456";
		String confirmPassword = "123456";
		Registration registeredUser = new Registration("TestFirstName", "TestLastName", "1234567890", "abc@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(registeredUser);
		String response = registrationService.register(firstName, lastName, contactNumber, emailAddress, password, confirmPassword);
		assertTrue(response.contains("This user is already registered"));
		
	}
	
	@DisplayName("test registration method with invalid email address")
	@Test
	public void testRegistrationInvalidContactNumber() {
		
		String firstName = "TestFirstName";
		String lastName = "TestLastName";
		String contactNumber = "12345678";
		String emailAddress = "abcgmail.com";
		String password = "123456";
		String confirmPassword = "123456";
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(null);
		String response = registrationService.register(firstName, lastName, contactNumber, emailAddress, password, confirmPassword);
		assertTrue(response.contains("Invalid Email Address"));
		
	}
	
	@DisplayName("test registration method when password and confirm password doesn't match")
	@Test
	public void testRegistrationPwdAndConfirmPwdMismactch() {
		
		String firstName = "TestFirstName";
		String lastName = "TestLastName";
		String contactNumber = "1234567890";
		String emailAddress = "abc@gmail.com";
		String password = "123456";
		String confirmPassword = "123455";
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(null);
		String response = registrationService.register(firstName, lastName, contactNumber, emailAddress, password, confirmPassword);
		assertTrue(response.contains("password & confirm password doesn't match"));
		
	}

	@DisplayName("test registration method successfully")
	@Test
	public void testRegistrationSuccessfully() {
		
		String firstName = "TestFirstName";
		String lastName = "TestLastName";
		String contactNumber = "1234567890";
		String emailAddress = "abc@gmail.com";
		String password = "123456";
		String confirmPassword = "123456";
		when(registrationRepository.readRegistrationRecordsByEmailAddress(contactNumber)).thenReturn(null);
		String response = registrationService.register(firstName, lastName, contactNumber, emailAddress, password, confirmPassword);
		assertTrue(response.contains("User Registration successful"));
		
	}




	
}
