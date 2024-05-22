package com.example.taskly.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegistrationDTO extends LoginDTO{
	
	private String username;
	private LocalDateTime whenJoin;
	private Boolean isActive;
	private LocalDateTime lastVisit;
}
