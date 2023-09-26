package com.example.taskly.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskType {
	SINGLE("Single"),
	REPEATED("Repeated");
	
	private final String name;
	
	TaskType(String name) {
	this.name = name;	
	}
	
	@JsonValue
	public String getName() {
		return name;
	}
	
}
