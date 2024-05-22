package com.example.taskly.enums;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskPriority {
	NO_SELECTED("No selected"),
	LOW("Low"),
	MEDIUM("Medium"),
	HIGH("High");
	
	private final String name;
	
	TaskPriority(String name) {
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return name;
	}
	
	public static Map<TaskPriority, String> getPriorityList() {
		Map<TaskPriority, String> levels = new TreeMap<>();
		for(TaskPriority level: TaskPriority.values()) {
			levels.putIfAbsent(level, level.getName());
		}
		return levels;
	}
}
