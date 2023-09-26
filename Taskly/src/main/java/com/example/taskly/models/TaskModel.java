package com.example.taskly.models;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.taskly.enums.PriorityLevel;
import com.example.taskly.enums.TaskCategory;
import com.example.taskly.enums.TaskState;
import com.example.taskly.enums.TaskType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
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
	
//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(
//			name="task_state_junction",
//			joinColumns= {@JoinColumn(name="task_id")},
//			inverseJoinColumns = {@JoinColumn(name="state_id")}
//			)
//	private String state;
	
	
	@Column(name="priority", nullable = false)
	@Enumerated(EnumType.STRING)
	private PriorityLevel priority;

//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(
//			name="task_priority_junction",
//			joinColumns = {@JoinColumn(name="task_id")},
//			inverseJoinColumns = {@JoinColumn(name="priority_id")}
//			)
//	private String priority;
	
	@Column(name="category", nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskCategory category;
	
//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(
//			name="task_category_junction",
//			joinColumns = {@JoinColumn(name="task_id")},
//			inverseJoinColumns= {@JoinColumn(name="category_id")}
//			)
//	private String category;
	
	@Column(name="note", nullable = true)
	private String note;
	
	@Column(name="type", nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskType type;
	
//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(
//			name="task_type_junction",
//			joinColumns = {@JoinColumn(name="task_id")},
//			inverseJoinColumns = {@JoinColumn(name="type_id")}
//			)
//	private String type;
}
