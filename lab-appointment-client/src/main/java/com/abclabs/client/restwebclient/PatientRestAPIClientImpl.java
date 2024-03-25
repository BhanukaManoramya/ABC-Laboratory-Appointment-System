package com.abclabs.client.restwebclient;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.abclabs.client.entity.Appointment;
import com.abclabs.client.entity.CustomerSupport;
import com.abclabs.client.entity.MedicalTestReport;
import com.abclabs.client.entity.Patient;
import com.abclabs.client.entity.PaymentCard;

@Component
public class PatientRestAPIClientImpl implements PatientRestAPIClient {

	Logger myLogger = Logger.getLogger(getClass().getName());

	@Autowired
	private RestTemplate restTemplate;

	@Value("${patient.business.url}")
	private String patientRestAPIUrl;

	@Value("${medreports.business.url}")
	private String medReportRestAPIUrl;

	@Value("${appointments.business.url}")
	private String appointmentRestAPIUrl;

	@Value("${emails.business.url}")
	private String emailRestAPIUrl;

	// ################################ PATIENT ############################################
	@Override
	public String saveOrUpdatePatient(Patient patient) {
		int id = patient.getId();
		if (id == 0) {
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(patientRestAPIUrl, patient,
					String.class);
			String message = responseEntity.getBody();
			System.out.println(message);
			return message;
		} else {
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
		restTemplate.delete(patientRestAPIUrl + "/" + patient.getId());
	}

	// ############################## MEDICAL REPORTS ######################################

	@Override
	public String saveOrUpdateTestReport(MedicalTestReport medicalTestReport) {
		int id = medicalTestReport.getId();
		if (id == 0) {
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(medReportRestAPIUrl, medicalTestReport,
					String.class);
			String message = responseEntity.getBody();
			return message;
		} else {
			restTemplate.put(medReportRestAPIUrl, medicalTestReport);
			return null;
		}
	}

	@Override
	public MedicalTestReport getTestReport(int id) {
		MedicalTestReport getTestReport = restTemplate.getForObject(medReportRestAPIUrl + "/" + id,
				MedicalTestReport.class);
		myLogger.info("MEDICAL REPORT: " + getTestReport);
		return getTestReport;
	}

	@Override
	public List<MedicalTestReport> getTestReports() {
		ResponseEntity<List<MedicalTestReport>> responseEntity = restTemplate.exchange(medReportRestAPIUrl,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<MedicalTestReport>>() {
				});
		List<MedicalTestReport> getTestReports = responseEntity.getBody();
		return getTestReports;
	}

	@Override
	public void deletePatientReport(MedicalTestReport medicalTestReport) {
		restTemplate.delete(medReportRestAPIUrl + "/" + medicalTestReport.getId());
	}

	// ############################### APPOINTMENT ###############################

	@Override
	public String saveOrUpdateAppointment(Appointment appointment) {
		int id = appointment.getId();
		if (id == 0) {
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(appointmentRestAPIUrl, appointment,
					String.class);
			String message = responseEntity.getBody();
			return message;
		} else {
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
		restTemplate.delete(appointmentRestAPIUrl + "/" + appointment.getId());
	}

	// ############################### EMAILS ###############################

	@Override
	public String sendEmails(PaymentCard paymentCard) {
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(emailRestAPIUrl, paymentCard, String.class);
		String message = responseEntity.getBody();
		return message;
	}

	@Override
	public String supportCustomer(CustomerSupport customerSupport) {
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(emailRestAPIUrl + "/support", customerSupport, String.class);
		String message = responseEntity.getBody();
		return message;
	}
}
