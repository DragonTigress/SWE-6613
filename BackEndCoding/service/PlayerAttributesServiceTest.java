package com.registration.app.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration.app.RegistrationApplication;
import com.registration.app.entity.Attribute;
import com.registration.app.entity.Registration;
import com.registration.app.repository.PlayerAttributesRepository;
import com.registration.app.repository.RegistrationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RegistrationApplication.class)
public class PlayerAttributesServiceTest {
	
	@InjectMocks
	PlayerAttributesService playerAttributesService;
	
	@Mock
	PlayerAttributesRepository playerAttributesRepository;
	
	@Mock
	RegistrationRepository registrationRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Before
	public void setup() {
		ReflectionTestUtils.setField(playerAttributesService,"objectMapper",objectMapper);
	}
	
	@DisplayName("test storeAttributes method for empty email address")
	@Test
	public void testStoreAttributes_emptyEmail() {
		String emailAddress = "";
		String timeZone = "EST";
		String microPhone = "Yes";
		String ageRange = "20-29";
		String gender = "female";
		List<String> consolePreference = new ArrayList<>();
		consolePreference.add("PC");
		consolePreference.add("xBox");
		
		String response  = playerAttributesService.storeAttributes(emailAddress, timeZone, microPhone, ageRange, gender, consolePreference);
		assertEquals("email address cannot be empty", response);	
	}
	
	@DisplayName("test storeAttributes method for empty timeZone")
	@Test
	public void testStoreAttributes_empty_timeZone() {
		String emailAddress = "abc@gmail.com";
		String timeZone = "";
		String microPhone = "Yes";
		String ageRange = "20-29";
		String gender = "female";
		List<String> consolePreference = new ArrayList<>();
		consolePreference.add("PC");
		consolePreference.add("xBox");
		
		String response  = playerAttributesService.storeAttributes(emailAddress, timeZone, microPhone, ageRange, gender, consolePreference);
		assertEquals("timeZone cannot be empty", response);			
	}
	
	@DisplayName("test storeAttributes method for empty micro phone")
	@Test
	public void testStoreAttributes_empty_microPhone() {
		String emailAddress = "abc@gmail.com";
		String timeZone = "EST";
		String microPhone = "";
		String ageRange = "20-29";
		String gender = "female";
		List<String> consolePreference = new ArrayList<>();
		consolePreference.add("PC");
		consolePreference.add("xBox");
		
		String response  = playerAttributesService.storeAttributes(emailAddress, timeZone, microPhone, ageRange, gender, consolePreference);
		assertEquals("microPhone cannot be empty", response);	
	}
	
	
	@DisplayName("test storeAttributes method for empty age range")
	@Test
	public void testStoreAttributes_empty_ageRange() {
		String emailAddress = "abc@gmail.com";
		String timeZone = "EST";
		String microPhone = "Yes";
		String ageRange = "";
		String gender = "female";
		List<String> consolePreference = new ArrayList<>();
		consolePreference.add("PC");
		consolePreference.add("xBox");
		
		String response  = playerAttributesService.storeAttributes(emailAddress, timeZone, microPhone, ageRange, gender, consolePreference);
		assertEquals("ageRange cannot be empty", response);		
	}
	
	@DisplayName("test storeAttributes method for empty gender")
	@Test
	public void testStoreAttributes_empty_gender() {
		String emailAddress = "abc@gmail.com";
		String timeZone = "EST";
		String microPhone = "Yes";
		String ageRange = "20-29";
		String gender = "";
		List<String> consolePreference = new ArrayList<>();
		consolePreference.add("PC");
		consolePreference.add("xBox");
		
		String response  = playerAttributesService.storeAttributes(emailAddress, timeZone, microPhone, ageRange, gender, consolePreference);
		assertEquals("gender cannot be empty", response);	
	}
	
	@DisplayName("test storeAttributes method for empty consolePreference list")
	@Test
	public void testStoreAttributes_empty_consolePreference() {
		String emailAddress = "abc@gmail.com";
		String timeZone = "EST";
		String microPhone = "Yes";
		String ageRange = "20-29";
		String gender = "Female";
		List<String> consolePreference = new ArrayList<>();
		
		String response  = playerAttributesService.storeAttributes(emailAddress, timeZone, microPhone, ageRange, gender, consolePreference);
		assertEquals("consolePreference cannot be empty", response);			
	}
	

	@DisplayName("test storeAttributes method for success scenario")
	@Test
	public void testStoreAttributes_success_scenario() {
		String emailAddress = "abc@gmail.com";
		String timeZone = "EST";
		String microPhone = "Yes";
		String ageRange = "20-29";
		String gender = "female";
		List<String> consolePreference = new ArrayList<>();
		consolePreference.add("PC");
		consolePreference.add("xBox");
		
		String response  = playerAttributesService.storeAttributes(emailAddress, timeZone, microPhone, ageRange, gender, consolePreference);
		assertEquals("Player attributes are successfully stored into the system", response);	
		
	}
	
	@DisplayName("test find match method when email address is empty")
	@Test
	public void testFindMatches_empty_emailAddress() {
		String emailAddress = "";
		String response = playerAttributesService.findMatches(emailAddress);
		assertEquals("Email Address is required", response);
		
	}
	
	@DisplayName("test find match method when player is not registered")
	@Test
	public void testFindMatches_player_is_not_registered() {
		String emailAddress = "aditi@gmail.com";
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(null);
		String response = playerAttributesService.findMatches(emailAddress);
		assertEquals("Player is not registered into the system aditi@gmail.com", response);
		
	} 
	@DisplayName("test find match method when player is registered but player's attributes are not stored into system")
	@Test
	public void testFindMatches_no_player_attributes() {
		String emailAddress = "aditi@gmail.com";
		Registration registration = new Registration("TestFirstName", "TestLastName","1234567890", "21", "Male", "abc@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(registration);
		when(playerAttributesRepository.readPlayerAttributeRecordsByEmailAddress(emailAddress)).thenReturn(null);
		String response = playerAttributesService.findMatches(emailAddress);
		assertEquals("To find the matching profiles, please provide your preferred attributes first ", response);

	}
	
	@DisplayName("test find match method when Match Found")
	@Test
	public void testFindMatches_Matches_Found(){
		String emailAddress = "aditi@gmail.com";
		Registration registration = new Registration("TestFirstName", "TestLastName", "1234567890", "21", "Male", "aditi@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(registration);
		Attribute attribute= new Attribute();
		attribute.setAgeRange("20-25");
		attribute.setGender("Female");
		attribute.setMicroPhone("Yes");
		attribute.setTimeZone("EST");
		attribute.setNintendoSwitch("Yes");
		attribute.setPc("Yes");
		attribute.setEmailAddress("aditi@gmail.com");
		when(playerAttributesRepository.readPlayerAttributeRecordsByEmailAddress(emailAddress)).thenReturn(attribute);
		
		Attribute otherplayersattribute= new Attribute();
		otherplayersattribute.setAgeRange("20-25");
		otherplayersattribute.setGender("male");
		otherplayersattribute.setMicroPhone("Yes");
		otherplayersattribute.setTimeZone("CST");
		otherplayersattribute.setNintendoSwitch("Yes");
		otherplayersattribute.setPc("Yes");
		otherplayersattribute.setEmailAddress("abc@gmail.com");

		
		List<Attribute> allPlayersAttributes= new ArrayList<>();
		allPlayersAttributes.add(otherplayersattribute);
		allPlayersAttributes.add(attribute);
		when(playerAttributesRepository.findAll()).thenReturn(allPlayersAttributes);
		
		Registration registration1 = new Registration("TestFirstName", "TestLastName","1234567890", "22", "Female", "abc@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress("abc@gmail.com")).thenReturn(registration1);
		
		String response = playerAttributesService.findMatches(emailAddress);
		assertNotNull(response);
			
	}
	
	@DisplayName("test find match method when Match Not Found because of age mismatch")
	@Test
	public void testFindMatches_age_misMatch(){
		String emailAddress = "aditi@gmail.com";
		Registration registration = new Registration("TestFirstName", "TestLastName", "1234567890", "21", "Male", "aditi@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(registration);
		Attribute attribute= new Attribute();
		attribute.setAgeRange("20-25");
		attribute.setGender("Female");
		attribute.setMicroPhone("Yes");
		attribute.setTimeZone("EST");
		attribute.setNintendoSwitch("Yes");
		attribute.setPc("Yes");
		attribute.setEmailAddress("aditi@gmail.com");
		when(playerAttributesRepository.readPlayerAttributeRecordsByEmailAddress(emailAddress)).thenReturn(attribute);
		
		Attribute otherplayersattribute= new Attribute();
		otherplayersattribute.setAgeRange("20-25");
		otherplayersattribute.setGender("male");
		otherplayersattribute.setMicroPhone("Yes");
		otherplayersattribute.setTimeZone("CST");
		otherplayersattribute.setNintendoSwitch("Yes");
		otherplayersattribute.setPc("Yes");
		otherplayersattribute.setEmailAddress("abc@gmail.com");

		
		List<Attribute> allPlayersAttributes= new ArrayList<>();
		allPlayersAttributes.add(otherplayersattribute);
		allPlayersAttributes.add(attribute);
		when(playerAttributesRepository.findAll()).thenReturn(allPlayersAttributes);
		
		Registration registration1 = new Registration("TestFirstName", "TestLastName","1234567890", "30", "Female", "abc@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress("abc@gmail.com")).thenReturn(registration1);
		
		String response = playerAttributesService.findMatches(emailAddress);
		assertNotNull(response);
		assertEquals("No Matches Found", response);
		
	}
	
	@DisplayName("test find match method when NO Match Found because of gender mismatch")
	@Test
	public void testFindMatchesage_gender_misMatch(){
		String emailAddress = "aditi@gmail.com";
		Registration registration = new Registration("TestFirstName", "TestLastName", "1234567890", "21", "Male", "aditi@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(registration);
		Attribute attribute= new Attribute();
		attribute.setAgeRange("20-25");
		attribute.setGender("Female");
		attribute.setMicroPhone("Yes");
		attribute.setTimeZone("EST");
		attribute.setNintendoSwitch("Yes");
		attribute.setPc("Yes");
		attribute.setEmailAddress("aditi@gmail.com");
		when(playerAttributesRepository.readPlayerAttributeRecordsByEmailAddress(emailAddress)).thenReturn(attribute);
		
		Attribute otherplayersattribute= new Attribute();
		otherplayersattribute.setAgeRange("20-25");
		otherplayersattribute.setGender("male");
		otherplayersattribute.setMicroPhone("Yes");
		otherplayersattribute.setTimeZone("CST");
		otherplayersattribute.setNintendoSwitch("Yes");
		otherplayersattribute.setPc("Yes");
		otherplayersattribute.setEmailAddress("abc@gmail.com");

		
		List<Attribute> allPlayersAttributes= new ArrayList<>();
		allPlayersAttributes.add(otherplayersattribute);
		allPlayersAttributes.add(attribute);
		when(playerAttributesRepository.findAll()).thenReturn(allPlayersAttributes);
		
		Registration registration1 = new Registration("TestFirstName", "TestLastName","1234567890", "22", "Male", "abc@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress("abc@gmail.com")).thenReturn(registration1);
		
		String response = playerAttributesService.findMatches(emailAddress);
		assertNotNull(response);
			
	}
	
	@DisplayName("test find match method when No Match Found because of microphone preference")
	@Test
	public void testFindMatches_Matches_Not_Found_microphone(){
		String emailAddress = "aditi@gmail.com";
		Registration registration = new Registration("TestFirstName", "TestLastName", "1234567890", "21", "Male", "aditi@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(registration);
		Attribute attribute= new Attribute();
		attribute.setAgeRange("20-25");
		attribute.setGender("Female");
		attribute.setMicroPhone("Yes");
		attribute.setTimeZone("EST");
		attribute.setNintendoSwitch("Yes");
		attribute.setPc("Yes");
		attribute.setEmailAddress("aditi@gmail.com");
		when(playerAttributesRepository.readPlayerAttributeRecordsByEmailAddress(emailAddress)).thenReturn(attribute);
		
		Attribute otherplayersattribute= new Attribute();
		otherplayersattribute.setAgeRange("20-25");
		otherplayersattribute.setGender("male");
		otherplayersattribute.setMicroPhone("No");
		otherplayersattribute.setTimeZone("CST");
		otherplayersattribute.setNintendoSwitch("Yes");
		otherplayersattribute.setPc("Yes");
		otherplayersattribute.setEmailAddress("abc@gmail.com");

		
		List<Attribute> allPlayersAttributes= new ArrayList<>();
		allPlayersAttributes.add(otherplayersattribute);
		allPlayersAttributes.add(attribute);
		when(playerAttributesRepository.findAll()).thenReturn(allPlayersAttributes);
		
		Registration registration1 = new Registration("TestFirstName", "TestLastName","1234567890", "22", "Female", "abc@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress("abc@gmail.com")).thenReturn(registration1);
		
		String response = playerAttributesService.findMatches(emailAddress);
		assertNotNull(response);
			
	}
	
	@DisplayName("test find match method when Match not Found because of console preference")
	@Test
	public void testFindMatches_Matches_Not_Found_console_preference(){
		String emailAddress = "aditi@gmail.com";
		Registration registration = new Registration("TestFirstName", "TestLastName", "1234567890", "21", "Male", "aditi@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress(emailAddress)).thenReturn(registration);
		Attribute attribute= new Attribute();
		attribute.setAgeRange("20-25");
		attribute.setGender("Female");
		attribute.setMicroPhone("Yes");
		attribute.setTimeZone("EST");
		attribute.setNintendoSwitch("Yes");
		attribute.setPc("Yes");
		attribute.setEmailAddress("aditi@gmail.com");
		when(playerAttributesRepository.readPlayerAttributeRecordsByEmailAddress(emailAddress)).thenReturn(attribute);
		
		Attribute otherplayersattribute= new Attribute();
		otherplayersattribute.setAgeRange("20-25");
		otherplayersattribute.setGender("male");
		otherplayersattribute.setMicroPhone("Yes");
		otherplayersattribute.setTimeZone("CST");
		otherplayersattribute.setxBox("Yes");
		otherplayersattribute.setEmailAddress("abc@gmail.com");

		
		List<Attribute> allPlayersAttributes= new ArrayList<>();
		allPlayersAttributes.add(otherplayersattribute);
		allPlayersAttributes.add(attribute);
		when(playerAttributesRepository.findAll()).thenReturn(allPlayersAttributes);
		
		Registration registration1 = new Registration("TestFirstName", "TestLastName","1234567890", "22", "Female", "abc@gmail.com", "123456");
		when(registrationRepository.readRegistrationRecordsByEmailAddress("abc@gmail.com")).thenReturn(registration1);
		
		String response = playerAttributesService.findMatches(emailAddress);
		assertNotNull(response);
			
	}
	
	

	
}
