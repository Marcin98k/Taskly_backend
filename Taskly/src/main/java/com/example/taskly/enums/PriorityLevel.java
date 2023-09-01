package com.example.taskly.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PriorityLevel {
	SELECT_PRIORITY("Select priority"),
	LOW("Low"),
	MEDIUM("Medium"),
	HIGH("High");
	
private final String name;
	
	private PriorityLevel(String name) {
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return name;
	}
}
