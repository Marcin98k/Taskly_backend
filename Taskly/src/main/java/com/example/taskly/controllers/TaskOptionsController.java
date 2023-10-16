package com.example.taskly.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskly.dto.OptionsDto;
import com.example.taskly.services.TaskOptionsService;

import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/options")
public class TaskOptionsController {

	private final TaskOptionsService taskOptionsService;
	
	public TaskOptionsController(TaskOptionsService taskOptionsService) {
		this.taskOptionsService = taskOptionsService;
	}
	
	@GetMapping("/status")
	public List<OptionsDto> statusOptions() {
		return taskOptionsService.getStatusOptions();
	}
	
	@GetMapping("/priority")
	public List<OptionsDto> priorityOptions() {
		return taskOptionsService.getPriorityOptions();
	}
	
	@GetMapping("/category")
	public List<OptionsDto> categoryOptions() {
		return taskOptionsService.getCategoryOptions();
	}
	
	@GetMapping("/type")
	public List<OptionsDto> typeOptions() {
		return taskOptionsService.getTypeOptions();
	}
}
