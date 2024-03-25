package com.abclabs.client.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.abclabs.client.entity.Patient;
import com.abclabs.client.restwebclient.PatientRestAPIClient;

@Controller
@RequestMapping("/register")
public class SignUpController {
	
	@Autowired
	PatientRestAPIClient restAPIClient;

	//Web request pre-processor
	@InitBinder
	public void processWebRequest(WebDataBinder webDataBinder) {
		StringTrimmerEditor myCustomStringEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, myCustomStringEditor);
	}

	@GetMapping("/page")
	public String showSignup(Model model) {
		model.addAttribute("patient", new Patient());
		return "signup";
	}
	
	@RequestMapping("/processform")
	public String registerPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult, Model model) {
		//########## DEBUG ##########
		System.out.println("|" + patient.getEmail() + "|");
		System.out.println(patient);
		System.out.println(bindingResult);
		//##############
		
		if (!bindingResult.hasErrors()) {
			//if validation passes..
			String status = restAPIClient.saveOrUpdatePatient(patient);
			System.out.println("s: " + status);
			model.addAttribute("signupStatus", status);
		}
		//if validation fails..
		return "signup";
	}
}
