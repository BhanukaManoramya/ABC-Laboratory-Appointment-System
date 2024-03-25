package com.abclabs.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

	@GetMapping("/redirect")
	public String showLoginPage() {
		return "page-redirect";
	}
	
	@GetMapping("/403")
	public String showAcessDenied() {
		return "access-denied";
	}
}
