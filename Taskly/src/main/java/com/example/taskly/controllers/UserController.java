package com.example.taskly.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskly.responses.ApiResponseDto;
import com.example.taskly.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PatchMapping("/account/{id}")
	public ResponseEntity<ApiResponseDto> partiallyChange(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		return userService.partllyUpdateUser(id, fields);
	}
	
	@DeleteMapping("/account/{id}")
	public ResponseEntity<ApiResponseDto> deleteAccount(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
}
