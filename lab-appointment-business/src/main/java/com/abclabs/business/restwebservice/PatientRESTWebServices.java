package com.abclabs.business.restwebservice;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abclabs.business.entity.Patient;
import com.abclabs.business.restwebclient.RESTWebClient;
import com.abclabs.business.utilities.PasswordEncryptor;

@RestController
@RequestMapping("/api")
public class PatientRESTWebServices {

	@Autowired
	RESTWebClient restWebClient;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@GetMapping("/patients")
	public List<Patient> getPatientList(){
		return restWebClient.getPatientList();
	}
	
	@GetMapping("/patients/{patientID}")
	public Patient getPatient(@PathVariable int patientID) {
		return restWebClient.getPatient(patientID);
	}
	
	@PostMapping("/patients")
	public String savePatient(@RequestBody Patient patient) {
		String password = PasswordEncryptor.encryptPassword(patient.getPassword());
		logger.info("Encrypted password: " + password);
		patient.setPassword("{bcrypt}" + password);
		return restWebClient.saveOrUpdatePatient(patient);
	}
	
	@PutMapping("/patients")
	public Patient updatePatient(@RequestBody Patient patient) {
		String password = PasswordEncryptor.encryptPassword(patient.getPassword());
		logger.info("Encrypted password: " + password);
		patient.setPassword("{bcrypt}" + password);
		restWebClient.saveOrUpdatePatient(patient);
		return patient;
	}

	@DeleteMapping("/patients/{patientID}")
	public void deletePatient(@PathVariable int patientID) {
		Patient patient = restWebClient.getPatient(patientID);
		restWebClient.deletePatient(patient);
	}
	
}
