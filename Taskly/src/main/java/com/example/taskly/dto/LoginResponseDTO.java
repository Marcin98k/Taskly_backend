package com.example.taskly.dto;

import com.example.taskly.models.ApplicationUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

	private ApplicationUser user;
	private String jwt;
	
	public LoginResponseDTO(String jwt) {
		super();
		this.jwt = jwt;
	}
}
