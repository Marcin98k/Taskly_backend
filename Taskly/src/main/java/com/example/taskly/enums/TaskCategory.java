package com.example.taskly.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskCategory {
	SELECT_CATEGORY("Select category"),
	HOME("Home"),
	WORK("Work"),
	HOBBY("Hobby"),
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
