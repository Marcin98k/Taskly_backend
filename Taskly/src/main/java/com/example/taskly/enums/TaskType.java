package com.example.taskly.enums;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskType {
	NO_SELECTED("No selected"),
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

	public static Map<TaskType, String> getTypeList() {
		Map<TaskType, String> types = new TreeMap<>();
		for(TaskType type: TaskType.values()) {
			types.putIfAbsent(type, type.getName());
		}
		return types;
	}
	
}
