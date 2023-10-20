package com.example.taskly.dto;

import java.time.LocalDateTime;

import com.example.taskly.models.AccountStatusModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
	
	private String username;
	private String email;
	private String password;
	private LocalDateTime whenJoin;
	private Boolean isActive;
	private AccountStatusModel accountStatusModel;
	private LocalDateTime lastVisit;
}
