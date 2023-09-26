package com.example.taskly.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskCategory {
	HOME("Home"),
	WORK("Work"),
	ELSE("Else");
	
	private final String name;
	
	TaskCategory(String name) {
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return name;
	}
	
}
