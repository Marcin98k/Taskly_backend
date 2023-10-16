package com.example.taskly.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskDto {
	
	private long id;
	private long userId;
	private String name;
	private LocalDateTime dateAdded;
	private LocalDateTime taskDate;
	private OptionsDto status;
	private OptionsDto priority;
	private OptionsDto category;
	private OptionsDto type;
	private String note;
}
