package com.registration.app.service;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.registration.app.RegistrationApplication;
import com.registration.app.repository.PlayerAttributesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RegistrationApplication.class)
public class PlayerAttributesServiceTest {
	
	@InjectMocks
	PlayerAttributesService playerAttributesService;
	
	@Mock
	PlayerAttributesRepository playerAttributesRepository;
	
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
}
