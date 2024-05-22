package com.example.taskly.enums;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskCategory {
	NO_SELECTED("No selected"),
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

	public static Map<TaskCategory, String> getCategoryList() {
		Map<TaskCategory, String> categories = new TreeMap<>();
		for(TaskCategory category: TaskCategory.values()) {
			categories.putIfAbsent(category, category.getName());
		}
		return categories;
	}
	
}
