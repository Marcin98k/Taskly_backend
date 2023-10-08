package com.example.taskly.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {
	
	private String email;
	private String username;
	private LocalDateTime whenJoin;
	private boolean isActive;
	private String role;
	private LocalDateTime lastVisit;
}
