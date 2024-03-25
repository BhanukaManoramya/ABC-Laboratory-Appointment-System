package com.abclabs.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abclabs.client.entity.ActiveUser;


@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping("/page")
	public String showLoginPage(Model model) {
		model.addAttribute("activeuser", new ActiveUser());
		return "login";
	}
	
	
}
