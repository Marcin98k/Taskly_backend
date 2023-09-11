package com.example.taskly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskly.models.ApplicationUser;
import com.example.taskly.models.LoginResponseDTO;
import com.example.taskly.models.RegistrationDTO;
import com.example.taskly.services.AuthorizationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class RoleController {
	
	@Autowired
	private AuthorizationService roleService;

	
	@PostMapping("/register")
	public ApplicationUser registerUser(@RequestBody RegistrationDTO registrationDTO) {
		return roleService.registerUser(registrationDTO.getUsername(), registrationDTO.getPassword());
	}
	
	@PostMapping("/login")
	public LoginResponseDTO loginUer(@RequestBody RegistrationDTO body) {
		return roleService.loginUser(body.getUsername(), body.getPassword());
	}
}

