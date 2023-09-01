package com.example.taskly.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskType {
	SINGLE("Single"),
	REAPEATED("Repeated");
	
	private final String name;
	
	TaskType(String name) {
	this.name = name;	
	}
	
	@JsonValue
	public String getName() {
		return name;
	}
	
}
