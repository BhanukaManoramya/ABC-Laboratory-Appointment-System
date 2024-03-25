package com.abclabs.business.entity;


public class MedicalTestReport {

	private int id;
	private String testType;
	private String patientName;
	private String reason;
	private String laboratory;
	private String dateTime;
	private String labTechnician;
	private String doctor;
	private String overallResult;
	private String labReport;
	
	public MedicalTestReport() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(String laboratory) {
		this.laboratory = laboratory;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getLabTechnician() {
		return labTechnician;
	}

	public void setLabTechnician(String labTechnician) {
		this.labTechnician = labTechnician;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getOverallResult() {
		return overallResult;
	}

	public void setOverallResult(String overallResult) {
		this.overallResult = overallResult;
	}

	public String getLabReport() {
		return labReport;
	}

	public void setLabReport(String labReport) {
		this.labReport = labReport;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

}
