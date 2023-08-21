package com.example.taskly.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.taskly.enums.TaskState;
import com.example.taskly.enums.PriorityLevel;
import com.example.taskly.enums.Categories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;


@Entity
@Data
@Table(name = "tasks")
public class TaskModel {

	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "userId")
	private long userId;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="startDate", nullable = false)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name="endDate", nullable = false)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column(name="state", nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskState state;
	
	@Column(name="priority", nullable = false)
	@Enumerated(EnumType.STRING)
	private PriorityLevel priority;
	
	@Column(name="categories", nullable = false)
	@Enumerated(EnumType.STRING)
	private Categories categories;
	
	@Column(name="note")
	private String note;
	
}
