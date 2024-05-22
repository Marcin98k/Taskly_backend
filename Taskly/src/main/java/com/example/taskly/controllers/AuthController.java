package com.example.taskly.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskly.dto.LoginDTO;
import com.example.taskly.dto.RegistrationDTO;
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

	@PostMapping("/sign-up")
	public String signUp(@RequestBody RegistrationDTO registrationDTO) {
		return authorizationService.signUp(registrationDTO);
	}
	
	@PostMapping("/sign-in")
	public String signIn(@RequestBody LoginDTO loginDto) {
		return authorizationService.signIn(loginDto);
	}
	
	@PostMapping("/token/{token}")
	public UserProperties decodeToken(@PathVariable String token) {
		return tokenService.decodeJwt(token);
	}
} 

