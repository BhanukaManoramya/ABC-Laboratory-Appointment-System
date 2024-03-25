package com.abclabs.business.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient {

	private int id;

	private String firstName;

	private String lastName;

	private String email;

	private String phone;

	private String address;

	private String gender;

	private String dateOfBirth;

	private String password;
	
	private List<MedicalTestReport> reportList;
	
	private Set<Appointment> appointmentList;

	public Patient() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Patient [first=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", password="
				+ password + "]";
	}

	public List<MedicalTestReport> getReportList() {
		return reportList;
	}

	public void setReportList(List<MedicalTestReport> reportList) {
		this.reportList = reportList;
	}

	public Set<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(Set<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}



}
