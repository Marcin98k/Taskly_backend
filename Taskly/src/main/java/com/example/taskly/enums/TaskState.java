package com.example.taskly.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskState {	
	ACTIVE("Active"),
	INACTIVE("Inactive"),
	STOPPED("Stopped"),
	WAITING("Waiting"),
	FINISHED("Finished");
	
	private final String name;
	
	TaskState(String name) {
		this.name = name; 
	}
	
	@JsonValue
	public String getName() {
		return name;
	}
}
