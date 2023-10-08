package com.example.taskly.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskly.dto.UserDto;
import com.example.taskly.models.ApplicationUser;
import com.example.taskly.responses.ApiResponseDto;
import com.example.taskly.services.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	private final UserService userService;

	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public List<ApplicationUser> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/users/{id}")
	public UserDto getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PatchMapping("/users/{id}")
	public ResponseEntity<ApiResponseDto> partllyUpdateUser(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		return userService.partllyUpdateUser(id, fields);
	}

	@DeleteMapping("users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
