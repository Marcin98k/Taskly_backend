package com.example.taskly.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskly.dto.LoginResponseDTO;
import com.example.taskly.dto.RegistrationDTO;
import com.example.taskly.models.ApplicationUser;
import com.example.taskly.models.UserProperties;
import com.example.taskly.services.AuthorizationService;
import com.example.taskly.services.TokenService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthorizationService authorizationService;
	private final TokenService tokenService;

	public AuthController(AuthorizationService authorizationService, TokenService tokenService) {
		this.authorizationService = authorizationService;
		this.tokenService = tokenService;
	}

	@PostMapping("/register")
	public ApplicationUser registerUser(@RequestBody RegistrationDTO registrationDTO) {
		return authorizationService.registerUser(registrationDTO.getUsername(), registrationDTO.getPassword());
	}
	
	@PostMapping("/login")
	public LoginResponseDTO loginUer(@RequestBody RegistrationDTO body) {
		return authorizationService.loginUser(body.getUsername(), body.getPassword());
	}
	
	@PostMapping("/token/{token}")
	public UserProperties decodeToken(@PathVariable String token) {
		return tokenService.decodeJwt(token);
	}
} 

