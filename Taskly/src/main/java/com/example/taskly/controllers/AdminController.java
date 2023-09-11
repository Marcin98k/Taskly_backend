package com.example.taskly.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	@PostMapping("/")
	public String helloAdmin() {
		return "Hello Admin";
	}
	
	@PostMapping("/token")
	public String mToken() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		return auth.toString();
	}
}
