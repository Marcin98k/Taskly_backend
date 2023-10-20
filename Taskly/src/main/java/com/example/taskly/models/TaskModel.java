package com.example.taskly.models;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "userId", nullable = false)
	private Long userId;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="dateAdded")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateAdded;
	
	@Column(name="taskDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime taskDate;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="status")
	private TaskStatusOptions status;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="priority")
	private TaskPriorityOptions priority;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category")
	private TaskCategoryOptions category;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="task")
	private TaskTypeOptions type;
	
	@Column(name="note", nullable = true)
	private String note;
}
