package com.example.taskly.models;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.taskly.enums.PeriodOfTime;
import com.example.taskly.enums.PriorityLevel;
import com.example.taskly.enums.TaskCategory;
import com.example.taskly.enums.TaskState;
import com.example.taskly.enums.TaskType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tasks")
public class TaskModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "userId", nullable = false)
	private long userId;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="startDate")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDate;
	
	@Column(name="endDate")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDate;
	
	@Column(name="state", nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskState state;
	
	@Column(name="priority", nullable = false)
	@Enumerated(EnumType.STRING)
	private PriorityLevel priority;
	
	@Column(name="category", nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskCategory category;
	
	@Column(name="note", nullable = true)
	private String note;
	
	@Column(name="type", nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskType type;
	
	public TaskModel() {
		
	}	

	public TaskModel(long userId, String name, LocalDateTime startDate, LocalDateTime endDate, TaskState state, PriorityLevel priority,
			TaskCategory category, String note, TaskType type) {
		super();
		this.userId = userId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.state = state;
		this.priority = priority;
		this.category = category;
		this.note = note;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public TaskState getState() {
		return state;
	}

	public void setState(TaskState state) {
		this.state = state;
	}

	public PriorityLevel getPriority() {
		return priority;
	}

	public void setPriority(PriorityLevel priority) {
		this.priority = priority;
	}

	public TaskCategory getCategory() {
		return category;
	}

	public void setCategory(TaskCategory category) {
		this.category = category;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public TaskType getType() {
		return type;
	}
	
	public void setType(TaskType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "\n name " + this.name +
				"\n userId " + this.userId +
				"\n startDate " + this.startDate +
				"\n endDate " + this.endDate +
				"\n startDate " + this.state +
				"\n priority " + this.priority +
				"\n category " + this.category +
				"\n note " + this.note +
				"\n type " + this.type;
	}
}
