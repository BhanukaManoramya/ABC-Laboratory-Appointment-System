package com.abclabs.client.controller;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.abclabs.client.entity.PaymentCard;
import com.abclabs.client.restwebclient.PatientRestAPIClient;

@Controller
@RequestMapping("/auth/patient")
public class PatientController {
	
	//####### Initialization code #########################################
	@Autowired
	PatientRestAPIClient restAPIClient;

	Logger myLogger = Logger.getLogger(getClass().getName());
	private Patient currentPatient = null;
	private List<MedicalTestReport> reportList = null;
	private Set<Appointment> appointmentList = null;
	private PaymentCard paymentCard = new PaymentCard();
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
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		myLogger.info("User: '" + currentUserName + "' logged in to the system");
		
		try {
			List<Patient> patientList = restAPIClient.getPatientList();
			for (Patient patient : patientList) {
				String tempUsername = patient.getFirstName() + patient.getLastName();
				if (tempUsername.toLowerCase().equals(currentUserName)) {
					
					currentPatient = patient;
					reportList = patient.getReportList();
					appointmentList = patient.getAppointmentList();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("currentPatient", currentPatient);
		model.addAttribute("testReports", reportList);
		model.addAttribute("appointments", appointmentList);
		model.addAttribute("paymentCard", paymentCard);
		if (message.trim().length() > 0) {
			model.addAttribute("updateStatus", message);
			message = "";
		}
		return "patient-dashboard";
	}

	@GetMapping("/processAppointment")
	public String processAppointments(@RequestParam("description") String description,
			@RequestParam("date") String date, @RequestParam("time") String time, Model model) {

		myLogger.info("Description: " + description + "date: " + date + "time: " + time);
		
		if (description == null || date == null || time == null) {
			message = "Appointment not successful! Make sure you fill all the required fields.";
		}else {
			String tempName = currentPatient.getFirstName() + " " + currentPatient.getLastName();
			Appointment appointment = new Appointment(tempName,  date, time, description);
			currentPatient.addAppointment(appointment);
			restAPIClient.saveOrUpdatePatient(currentPatient);
			
			message = "Appointment successful! wait for the confirmation in order to get the number to undergo the test and laboratory information.";
		}
		
		return "redirect:/auth/patient/home";
	}

	@PostMapping("/processProfile")
	public String processProfile(@Valid @ModelAttribute("currentPatient") Patient updatedPatient,
			BindingResult bindingResult, Model model) {
		
		if (!bindingResult.hasErrors()) {
			updatedPatient.setReportList(reportList);
			updatedPatient.setAppointmentList(appointmentList);
			restAPIClient.saveOrUpdatePatient(updatedPatient);
			message = "Profile has been successfully updated!";
			currentPatient = updatedPatient;
		} else {
			System.out.println("BINDING RESULT: " + bindingResult);
			message = "Profile not updated! Please check your form data and try again!";
		}

		return "redirect:/auth/patient/home";
	}
	
	@PostMapping("/processPayment")
	public String processPayments(@Valid @ModelAttribute("paymentCard") PaymentCard paymentCard,
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			System.out.println("BINDING RESULT: " + bindingResult);
			this.paymentCard = paymentCard;
			message = "Invalid credit card info. Payment was not successful!";
		}else {
			System.out.println("Payment is processing....");
			paymentCard.setHolderEmailAddress(currentPatient.getEmail());
			
			//send emails
			restAPIClient.sendEmails(paymentCard);
			message = "Payment was successful! Thank you.";
			this.paymentCard = new PaymentCard();
		}
		return "redirect:/auth/patient/home";
	}

}
