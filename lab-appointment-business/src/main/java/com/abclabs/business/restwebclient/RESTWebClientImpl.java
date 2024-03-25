package com.abclabs.business.restwebclient;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.abclabs.business.entity.Appointment;
import com.abclabs.business.entity.MedicalTestReport;
import com.abclabs.business.entity.Patient;


@Component
public class RESTWebClientImpl implements RESTWebClient {

	Logger myLogger = Logger.getLogger(getClass().getName());

	@Autowired
	private RestTemplate restTemplate;

	@Value("${patient.repository.url}")
	private String patientRestAPIUrl;

	@Value("${medreports.repository.url}")
	private String medReportRestAPIUrl;

	@Value("${appointments.repository.url}")
	private String appointmentRestAPIUrl;

	//################################ PATIENT ############################################
	
	@Override
	public String saveOrUpdatePatient(Patient patient) {
		int id = patient.getId();
		myLogger.info("ID" + id);
		if (id == 0) {
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(patientRestAPIUrl, patient, String.class);
			String message = responseEntity.getBody();
			return message;
		}else {
			restTemplate.put(patientRestAPIUrl, patient);
			return null;
		}
	}
	
	@Override
	public Patient getPatient(int id) {
		Patient patient = restTemplate.getForObject(patientRestAPIUrl + "/" + id, Patient.class);
		myLogger.info("PATIENT: " + patient);
		return patient;
	}

	@Override
	public List<Patient> getPatientList() {
		ResponseEntity<List<Patient>> responseEntity = restTemplate.exchange(patientRestAPIUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Patient>>() {
				});
		List<Patient> patientList = responseEntity.getBody();
		return patientList;
	}
	
	@Override
	public void deletePatient(Patient patient) {
		restTemplate.delete(patientRestAPIUrl+ "/"+ patient.getId());
	}


	//############################## MEDICAL REPORTS ######################################

	@Override
	public String saveOrUpdateTestReport(MedicalTestReport medicalTestReport) {
		int id = medicalTestReport.getId();
		if (id == 0) {
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(medReportRestAPIUrl, medicalTestReport,
					String.class);
			String message = responseEntity.getBody();
			return message;
		}else {
			restTemplate.put(medReportRestAPIUrl, medicalTestReport);
			return null;
		}
	}

	@Override
	public MedicalTestReport getTestReport(int id) {
		MedicalTestReport testReport = restTemplate.getForObject(medReportRestAPIUrl + "/" + id,
				MedicalTestReport.class);
		myLogger.info("MEDICAL REPORT: " + testReport);
		return testReport;
	}

	@Override
	public List<MedicalTestReport> getTestReports() {
		ResponseEntity<List<MedicalTestReport>> responseEntity = restTemplate.exchange(medReportRestAPIUrl,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<MedicalTestReport>>() {
				});
		List<MedicalTestReport> testReports = responseEntity.getBody();
		return testReports;
	}
	
	@Override
	public void deleteTestReport(MedicalTestReport medicalTestReport) {
		restTemplate.delete(medReportRestAPIUrl+ "/"+ medicalTestReport.getId());
	}

	//############################### APPOINTMENTS ###############################

	@Override
	public String saveOrUpdateAppointment(Appointment appointment) {
		int id = appointment.getId();
		if (id == 0) {
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(appointmentRestAPIUrl, appointment,
					String.class);
			String message = responseEntity.getBody();
			return message;
		}else {
			restTemplate.put(appointmentRestAPIUrl, appointment);
			return null;
		}
	}

	@Override
	public Appointment getAppointment(int id) {
		Appointment appointment = restTemplate.getForObject(appointmentRestAPIUrl + "/" + id, Appointment.class);
		myLogger.info("APPOINTMENT: " + appointment);
		return appointment;
	}

	@Override
	public List<Appointment> getAppointments() {
		ResponseEntity<List<Appointment>> responseEntity = restTemplate.exchange(appointmentRestAPIUrl, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Appointment>>() {
				});
		List<Appointment> appointmentList = responseEntity.getBody();
		return appointmentList;
	}
	
	@Override
	public void deleteAppointment(Appointment appointment) {
		restTemplate.delete(appointmentRestAPIUrl+ "/"+ appointment.getId());
	}
	
}
