package com.example.taskly.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PriorityLevel {
	LOW("Low"),
	MEDIUM("Medium"),
	HIGH("High");
	
	private final String name;
	
	PriorityLevel(String name) {
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return name;
	}
}
