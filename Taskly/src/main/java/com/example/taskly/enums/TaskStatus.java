package com.example.taskly.enums;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {	
	INACTIVE("Inactive"),
	ACTIVE("Active"),
	STOPPED("Stopped"),
	WAITING("Waiting"),
	FINISHED("Finished");
	
	private final String name;
	
	TaskStatus(String name) {
		this.name = name; 
	}
	
	@JsonValue
	public String getName() {
		return name;
	}

	public static Map<TaskStatus, String> getStatusList() {
		Map<TaskStatus, String> statuses = new TreeMap<>();
		for(TaskStatus status: TaskStatus.values()) {
			statuses.putIfAbsent(status, status.getName());
		}
		return statuses;
	}
}
