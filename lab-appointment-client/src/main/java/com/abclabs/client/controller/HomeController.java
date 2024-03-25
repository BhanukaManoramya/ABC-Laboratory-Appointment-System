package com.abclabs.client.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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

import com.abclabs.client.entity.CustomerSupport;
import com.abclabs.client.restwebclient.PatientRestAPIClient;

@Controller
public class HomeController {

	@Autowired
	PatientRestAPIClient restAPIClient;

	@GetMapping("/")
	public String showHome(Model model) {
		model.addAttribute("customerSupport", new CustomerSupport());
		return "index";
	}

	@GetMapping("/home")
	public String redirectHome(Model model) {
		model.addAttribute("customerSupport", new CustomerSupport());
		return "redirect:/";
	}

	// Web request pre-processor
	@InitBinder
	public void processWebRequest(WebDataBinder webDataBinder) {
		StringTrimmerEditor myCustomStringEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, myCustomStringEditor);
	}

	@PostMapping("/contact")
	public String supportCustomers(@ModelAttribute("customerSupport") CustomerSupport customerSupport,
			Model model, BindingResult bindingResult, HttpServletResponse response) {
		String message = "";
		
		//Make use of cookies
		Cookie cookie = new Cookie("customer" + Math.random(), customerSupport.getName());
		cookie.setMaxAge(60000);
		response.addCookie(cookie);
		
		System.out.println(customerSupport);
		if (customerSupport.getName() == null || customerSupport.getEmail() == null 
				|| customerSupport.getMessage() == null) {
			message = "Form fields cannot be empty! Please try again in order to support your needs.";
		}else {
			message = restAPIClient.supportCustomer(customerSupport);
		}
		model.addAttribute("message", message);
		return "index";
	}

}
