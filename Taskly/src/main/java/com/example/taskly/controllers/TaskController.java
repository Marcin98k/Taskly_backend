package com.example.taskly.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.taskly.dto.TaskDto;
import com.example.taskly.models.TaskModel;
import com.example.taskly.responses.ApiResponseDto;
import com.example.taskly.services.TaskService;

@CrossOrigin(origins = "*")
@EnableWebMvc
@RestController
@RequestMapping("/task")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@PostMapping
	public TaskDto createTask(@RequestBody TaskDto taskDto) {
		return taskService.createTask(taskDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskModel taskModelDetails) {
		return taskService.updateTask(id, taskModelDetails);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponseDto> partllyUpdateTask(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		return taskService.partllyUpdateTask(id, fields);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseDto> deleteTask(@PathVariable Long id) {
		return taskService.deleteTask(id);
	}
	
	@GetMapping("/{id}")
	public TaskDto getTask(@PathVariable Long id) {
		return taskService.getTask(id);
	}
	
	@GetMapping
	public List<TaskDto> getAllTasks() {
		return taskService.getAllTasks();
	}
	
//	@PreAuthorize("#userId == principal.id")
	@GetMapping("/showUserTasks/{userId}")
	public List<TaskDto> getUserTaskById(@PathVariable Long userId) {
		System.out.println("I was here MArCin");
		return taskService.getUserTask(userId);
	}
	
	@GetMapping("/showAllCompletedTasks/{userId}")
	public List<TaskDto> getFinishedTask(@PathVariable Long userId) {
		return taskService.getFinishedTask(userId);
	}
	
	@GetMapping("/showCurrentTasks/{userId}")
	public List<TaskDto> getCurrentTask(@PathVariable Long userId) {
		return taskService.getCurrentTask(userId);
	}
	
	@GetMapping("/showTaskByCategory/{userId}/{category}")
	public List<TaskDto> getTaskByCategory(@PathVariable Long userId, @PathVariable String category) {
		return taskService.getTaskByCategory(userId, category);
	}
	
	@GetMapping("/countAllTask/{userId}")
	public Long getTaskCount(@PathVariable Long userId) {
		return taskService.getAllTaskCount(userId);
	}
	
	@GetMapping("/countCompletedTask/{userId}")
	public Long getCompletedTaskCount(@PathVariable Long userId) {
		return taskService.getCompletedTaskCount(userId);
	}
	
	@GetMapping("/countActiveTask/{userId}")
	public Long getActiveCount(@PathVariable Long userId) {
		return taskService.getActiveCount(userId);
	}
}
