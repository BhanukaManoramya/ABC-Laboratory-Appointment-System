package com.abclabs.client.restwebclient;

import java.util.List;

import com.abclabs.client.entity.Appointment;
import com.abclabs.client.entity.CustomerSupport;
import com.abclabs.client.entity.MedicalTestReport;
import com.abclabs.client.entity.Patient;
import com.abclabs.client.entity.PaymentCard;

public interface PatientRestAPIClient {

	//################################ PATIENT ############################################
	String saveOrUpdatePatient(Patient patient);
	Patient getPatient(int id);
	List<Patient> getPatientList();
	void deletePatient(Patient patient);
	
	//############################## MEDICAL REPORTS ######################################
	String saveOrUpdateTestReport(MedicalTestReport medicalTestReport);
	MedicalTestReport getTestReport(int id);
	List<MedicalTestReport> getTestReports();
	void deletePatientReport(MedicalTestReport medicalTestReport);
	
	//############################### APPOINTMENTS ###############################
	String saveOrUpdateAppointment(Appointment appointment);
	Appointment getAppointment(int id);
	List<Appointment> getAppointments();
	void deleteAppointment(Appointment appointment);
	
	//############################### EMAILS ###############################
	String sendEmails(PaymentCard paymentCard);
	String supportCustomer(CustomerSupport customerSupport);
}
