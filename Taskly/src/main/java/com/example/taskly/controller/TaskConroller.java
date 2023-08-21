package com.example.taskly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskly.model.TaskModel;
import com.example.taskly.repository.TaskRepository;

@RestController
@RequestMapping("/api/v1/task")
public class TaskConroller {

	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/task")
	public List<TaskModel> getAllTasks() {
		return taskRepository.findAll();
	}
}
