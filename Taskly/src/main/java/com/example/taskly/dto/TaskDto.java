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
	private String status;
	private String priority;
	private String category;
	private String type;
	private String note;
}
