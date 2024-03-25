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

import com.abclabs.business.entity.MedicalTestReport;
import com.abclabs.business.restwebclient.RESTWebClient;

@RestController
@RequestMapping("/api")
public class MedReportRESTWebServices {

	@Autowired
	RESTWebClient restWebClient;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@GetMapping("/medreports")
	public List<MedicalTestReport> getTestReports(){
		return restWebClient.getTestReports();
	}
	
	@GetMapping("/medreports/{reportID}")
	public MedicalTestReport getTestReport(@PathVariable int reportID) {
		return restWebClient.getTestReport(reportID);
	}
	
	@PostMapping("/medreports")
	public String saveTestReport(@RequestBody MedicalTestReport medicalTestReport) {
		return restWebClient.saveOrUpdateTestReport(medicalTestReport);
	}
	
	@PutMapping("/medreports")
	public MedicalTestReport updateTestReport(@RequestBody MedicalTestReport medicalTestReport) {
		restWebClient.saveOrUpdateTestReport(medicalTestReport);
		return medicalTestReport;
	}

	@DeleteMapping("/medreports/{reportID}")
	public void deleteTestReport(@PathVariable int reportID) {
		MedicalTestReport medicalTestReport = restWebClient.getTestReport(reportID);
		restWebClient.deleteTestReport(medicalTestReport);
	}

	
}
