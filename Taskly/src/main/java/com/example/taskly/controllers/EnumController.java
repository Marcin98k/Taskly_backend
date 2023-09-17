package com.example.taskly.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskly.enums.PriorityLevel;
import com.example.taskly.enums.TaskCategory;
import com.example.taskly.enums.TaskState;
import com.example.taskly.enums.TaskType;
import com.example.taskly.repositories.TaskRepository;

@CrossOrigin(origins = "*")
@RequestMapping("/enum")
@RestController
public class EnumController {

	private final TaskRepository taskRepository;

	public EnumController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@GetMapping("/task-state")
	public TaskState[] getTaskStateValues() {
		return TaskState.values();
	}

	@GetMapping("/task-priority")
	public PriorityLevel[] getTaskPriorityValues() {
		return PriorityLevel.values();
	}

	@GetMapping("/task-categories")
	public TaskCategory[] getTaskCategoryValues() {
		return TaskCategory.values();
	}

	@GetMapping("/task-type")
	public TaskType[] getTaskTypeValues() {
		return TaskType.values();
	}
}
