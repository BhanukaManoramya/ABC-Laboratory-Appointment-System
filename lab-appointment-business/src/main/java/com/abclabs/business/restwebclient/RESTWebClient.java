package com.abclabs.business.restwebclient;

import java.util.List;

import com.abclabs.business.entity.Appointment;
import com.abclabs.business.entity.MedicalTestReport;
import com.abclabs.business.entity.Patient;

public interface RESTWebClient {

	//################################ PATIENT ############################################
	String saveOrUpdatePatient(Patient patient);
	Patient getPatient(int id);
	List<Patient> getPatientList();
	void deletePatient(Patient patient);
	
	//############################## MEDICAL REPORTS ######################################
	String saveOrUpdateTestReport(MedicalTestReport medicalTestReport);
	MedicalTestReport getTestReport(int id);
	List<MedicalTestReport> getTestReports();
	void deleteTestReport(MedicalTestReport medicalTestReport);
	
	//############################### APPOINTMENT ###############################
	String saveOrUpdateAppointment(Appointment appointment);
	Appointment getAppointment(int id);
	List<Appointment> getAppointments();
	void deleteAppointment(Appointment appointment);
	
	
}
