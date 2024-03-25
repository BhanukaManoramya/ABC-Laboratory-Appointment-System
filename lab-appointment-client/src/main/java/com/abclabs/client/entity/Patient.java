package com.abclabs.client.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.abclabs.client.validationcontraints.*;

@MatchField.List({ @MatchField(first = "password", second = "conPassword", message = "Password fields must match") })
public class Patient {

	private int id;

	@NotNull(message = "required!")
	private String firstName;

	@NotNull(message = "required!")
	private String lastName;

	@NotNull(message = "required!")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Invalid email address")
	private String email;

	@NotNull(message = "required!")
	@Pattern(regexp = "^[0-9]{10}", message = "Invalid phone number")
	private String phone;

	@NotNull(message = "required!")
	@Size(max = 100, message = "you have exceeded the character limit (100).")
	private String address;

	@NotNull(message = "required!")
	private String gender;

	@NotNull(message = "required!")
	@Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])([-/ ]?)(0?[1-9]|1[0-2])\\2(?:19|20)[0-9]{2}", message = "Invalid date format!")
	private String dateOfBirth;

	@NotNull(message = "required!")
	@Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "Minimum 8 Character Password with lowercase, uppercase letters and numbers required!")
	private String password;

	@NotNull(message = "required!")
	private String conPassword;

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

	public String getConPassword() {
		return conPassword;
	}

	public void setConPassword(String conPassword) {
		this.conPassword = conPassword;
	}

	@Override
	public String toString() {
		return "Patient [first=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", password="
				+ password + ", conPassword=" + conPassword + "]";
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

	public void addAppointment(Appointment appointment) {
		if (appointmentList == null) {
			appointmentList = new HashSet<>();
		}
		appointmentList.add(appointment);
	}
	
	public void addMedicalReport(MedicalTestReport medicalTestReport) {
		if (reportList == null) {
			reportList = new ArrayList<>();
		}
		reportList.add(medicalTestReport);
	}
}
