package com.example.taskly.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProperties {
	
	private Long id;
	private String email;
	private String role;	
}
