package com.abclabs.client.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abclabs.client.entity.Appointment;
import com.abclabs.client.entity.MedicalTestReport;
import com.abclabs.client.entity.Patient;
import com.abclabs.client.restwebclient.PatientRestAPIClient;

@Controller
@RequestMapping("/auth/manager")
public class ManagerController {
	
	//####### Initialization code #########################################
		@Autowired
		PatientRestAPIClient restAPIClient;

		Logger myLogger = Logger.getLogger(getClass().getName());
		
		List<Appointment> appointments;
		List<MedicalTestReport> testReports;
		List<Patient> patientAll;
		String message = "";
		// Web request pre-processor
		@InitBinder
		public void processWebRequest(WebDataBinder webDataBinder) {
			StringTrimmerEditor myCustomStringEditor = new StringTrimmerEditor(true);
			webDataBinder.registerCustomEditor(String.class, myCustomStringEditor);
		}
		
		//################################################

		@RequestMapping("/home")
		public String showHome(Model model) {
			
			appointments = restAPIClient.getAppointments();
			testReports = restAPIClient.getTestReports();
			patientAll = restAPIClient.getPatientList();
			
			model.addAttribute("appointmentList", appointments);
			model.addAttribute("reportList", testReports);
			model.addAttribute("patientList", patientAll);
			model.addAttribute("medReport", new MedicalTestReport());
			model.addAttribute("selectedAppointment", new Appointment());
			
			if (message.trim().length() > 0) {
				model.addAttribute("updateStatus", message);
				message = "";
			}
			return "manager-dashboard";
		}

		@GetMapping("/loadAppointment")
		public String loadAppointment(@RequestParam("patientID") int patientID, Model model) {
			Appointment appointment = restAPIClient.getAppointment(patientID);
			appointment.setEnabled("1");
			model.addAttribute("appointmentList", appointments);
			model.addAttribute("selectedAppointment", appointment);
			model.addAttribute("reportList", testReports);
			model.addAttribute("medReport", new MedicalTestReport());
			return "manager-dashboard";
		}
		
		@PostMapping("/confirmAppointment")
		public String confirmAppointment(@Valid @ModelAttribute("selectedAppointment") Appointment appointment, 
				BindingResult bindingResult, Model model) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("appointmentList", appointments);
				model.addAttribute("reportList", testReports);
				model.addAttribute("selectedAppointment", appointment);
				model.addAttribute("medReport", new MedicalTestReport());
				return "manager-dashboard";
			}else {
				restAPIClient.saveOrUpdateAppointment(appointment);
				return "redirect:/auth/manager/home";
			}
		}
		
		@PostMapping("/saveReport")
		public String saveOrUpdateReport(@ModelAttribute("medReport") MedicalTestReport medicalTestReport, 
				Model model) {
			
			String tempName = medicalTestReport.getPatientName().toLowerCase();
			List<Patient> patients = restAPIClient.getPatientList();
			for (Patient patient : patients) {
				String patientFullName = patient.getFirstName() + " " + patient.getLastName();
				if (patientFullName.toLowerCase().equals(tempName)) {
					
					String currentDate = new SimpleDateFormat("dd/MM/yyyy hh:mm a, z").format(new Date());
					medicalTestReport.setDateTime(currentDate);
					
					patient.addMedicalReport(medicalTestReport);
					restAPIClient.saveOrUpdatePatient(patient);
					message = "Submission of patient's lab report is confirmed!";
					break;
				}
			}
			
			return "redirect:/auth/manager/home";
		}
		
		@GetMapping("/deleteReport")
		public String deleteReport(@RequestParam("reportID") int reportID, Model model) {
			MedicalTestReport medicalTestReport = restAPIClient.getTestReport(reportID);
			if (medicalTestReport != null) {
				restAPIClient.deletePatientReport(medicalTestReport);
			}
			return "redirect:/auth/manager/home";
		}
		
}
