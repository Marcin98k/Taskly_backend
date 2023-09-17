package com.example.taskly.controllers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskly.exceptions.ResourceNotFoundException;
import com.example.taskly.models.ApplicationUser;
import com.example.taskly.repositories.UserRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping("/")
	public String helloUser() {
		return "Hello User";
	}
	
	@PatchMapping("/update-account/{id}")
	public ResponseEntity<ApplicationUser> partiallyChange(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		ApplicationUser user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with this id: (" + id + ") not exist"));
	
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(ApplicationUser.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, user, value);
		});
		ApplicationUser success = userRepository.save(user);
		return ResponseEntity.ok(success);
	}
	
	@DeleteMapping("/delete-account/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteAccount(@PathVariable Long id) {
		ApplicationUser user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with this id: (" + id + ") not exist"));
		userRepository.delete(user);
		Map<String, Boolean> score = new HashMap<>();
		score.put("delete", Boolean.TRUE);
		return ResponseEntity.ok(score);
	}
}
