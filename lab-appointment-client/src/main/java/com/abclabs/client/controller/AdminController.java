package com.abclabs.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/admin")
public class AdminController {

	@RequestMapping("/home")
	public String test(Model model) {
		//model.addAttribute("user", new User());
		return "admin-dashboard";
	}
	
	@PostMapping("/saveorupdate")
	public String test() {
		return "test";
	}
}
