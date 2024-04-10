package com.registration.app.response;

import java.util.ArrayList;
import java.util.List;

public class Profile {
	
	String firstName;
	String lastName;
	String age;
	String gender;
	String microPhone;
	List<String> consolePreferences;
	String timeZone;
	
	public Profile() {
		this.firstName = null;
		this.lastName = null;
		this.age = null;
		this.gender = null;
		this.microPhone = null;
		this.consolePreferences = new ArrayList<>();
		this.timeZone = null;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMicroPhone() {
		return microPhone;
	}
	public void setMicroPhone(String microPhone) {
		this.microPhone = microPhone;
	}
	public List<String> getConsolePreferences() {
		return consolePreferences;
	}
	public void setConsolePreferences(List<String> consolePreferences) {
		this.consolePreferences = consolePreferences;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	
}
