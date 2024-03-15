package com.registration.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Attribute {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String emailAddress;
	
	@Column
	private String timeZone;
	
	@Column
	private String microPhone;
	
	@Column
	private String ageRange;
	
	@Column
	private String gender;
	
	@Column
	private String pc = "No";
	
	@Column
	private String xBox = "No";
	
	@Column
	private String playStation = "No";
	
	@Column
	private String nintendoSwitch = "No";
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getMicroPhone() {
		return microPhone;
	}

	public void setMicroPhone(String microPhone) {
		this.microPhone = microPhone;
	}

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getxBox() {
		return xBox;
	}

	public void setxBox(String xBox) {
		this.xBox = xBox;
	}

	public String getPlayStation() {
		return playStation;
	}

	public void setPlayStation(String playStation) {
		this.playStation = playStation;
	}

	public String getNintendoSwitch() {
		return nintendoSwitch;
	}

	public void setNintendoSwitch(String nintendoSwitch) {
		this.nintendoSwitch = nintendoSwitch;
	}

}
