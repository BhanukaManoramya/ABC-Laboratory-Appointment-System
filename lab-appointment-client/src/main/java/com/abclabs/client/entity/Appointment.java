package com.abclabs.client.entity;

import javax.validation.constraints.NotNull;


public class Appointment {

	private int id;
	private String patientName;
	@NotNull(message = "required")
	private String date;
	@NotNull(message = "required")
	private String time;
	@NotNull(message = "Description required!")
	private String description;
	private String laboratory;
	@NotNull(message = "required")
	private String patientNumber;
	@NotNull(message = "required")
	private String enabled;
	

	public Appointment() {
	}

	public Appointment(String patientName, 
			@NotNull(message = "required") String date, @NotNull(message = "required") String time,
			@NotNull(message = "Description required!") String description) {
		this.patientName = patientName;
		this.date = date;
		this.time = time;
		this.description = description;
		this.laboratory = null;
//		this.patientNumber = 0;
//		this.enabled = 0;
	}

	public Integer getId() {
		if (id == 0) {
			return null;
		}
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(String laboratory) {
		this.laboratory = laboratory;
	}

	public String getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", time=" + time + ", description=" + description
				+ ", laboratory=" + laboratory + ", patientNumber=" + patientNumber + ", enabled=" + enabled + "]";
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

}
