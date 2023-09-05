package com.example.taskly.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PeriodOfTime {
	NONE("None"),
	DAILY("Daily"),
	EVERY_SECOND_DAY("Every second day"),
	EVERY_THIRD_DAY("Every third day"),
	EVERY_WEEK("Every week");
	
	private final String name;
	
	PeriodOfTime(String name) {
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return name;
	}

}
