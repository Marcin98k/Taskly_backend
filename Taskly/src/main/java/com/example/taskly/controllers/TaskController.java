package com.example.taskly.controllers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
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

import com.example.taskly.enums.PriorityLevel;
import com.example.taskly.enums.TaskCategory;
import com.example.taskly.enums.TaskState;
import com.example.taskly.enums.TaskType;
import com.example.taskly.exceptions.ResourceNotFoundException;
import com.example.taskly.models.TaskModel;
import com.example.taskly.repositories.TaskRepository;

@CrossOrigin(origins = "http://localhost:4200")
@EnableWebMvc
@RestController
@RequestMapping("/api/v1/")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/showAllTasks")
	public List<TaskModel> getAllTasks() {
		return taskRepository.findAll();
	}
	
	@GetMapping("/showUserTasks/{userId}") 
	public List<TaskModel> getUserTaskById(@PathVariable Long userId) {
		return taskRepository.findByUserId(userId);
	}
	
	@GetMapping("/task-state-enums")
	public TaskState[] getTaskStateValues() {
		return TaskState.values();
	}
	
	@GetMapping("/task-priority-enums")
	public PriorityLevel[] getTaskPriorityValues() {
		return PriorityLevel.values();
	}
	
	@GetMapping("/task-categories-enums")
	public TaskCategory[] getTaskCategoryValues() {
		return TaskCategory.values();
	}
	
	@GetMapping("/task-type-enums")
	public TaskType[] getTaskTypeValues() {
		return TaskType.values();
	}
	
//	Create task;
	@PostMapping("/showAllTasks")
	public TaskModel createTaskModel(@RequestBody TaskModel taskModel) {
	return taskRepository.save(taskModel);
	}
	
//	Get task;
	@GetMapping("/showAllTasks/{id}")
	public ResponseEntity<TaskModel> getTaskById(@PathVariable Long id) {
		TaskModel taskModel = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task with this id: (" + id + ") not exist"));
		return ResponseEntity.ok(taskModel);
	}
	
//	Update task;
	@PutMapping("/showAllTasks/{id}")
	public ResponseEntity<TaskModel> updateTask(@PathVariable Long id, @RequestBody TaskModel taskModelDetails) {
		TaskModel taskModel = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task with this id: (" + id + ") not exist"));
		
		taskModel.setName(taskModelDetails.getName());
		taskModel.setUserId(taskModelDetails.getUserId());
		taskModel.setStartDate(taskModelDetails.getStartDate());
		taskModel.setEndDate(taskModelDetails.getEndDate());
		taskModel.setState(taskModelDetails.getState());
		taskModel.setPriority(taskModelDetails.getPriority());
		taskModel.setCategory(taskModelDetails.getCategory());
		taskModel.setNote(taskModelDetails.getNote());
		taskModel.setType(taskModelDetails.getType());
		
		TaskModel updateTask = taskRepository.save(taskModel);
		return ResponseEntity.ok(updateTask);
	}
	
//	Delete task;
	@DeleteMapping("/showAllTasks/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable Long id) {
		TaskModel taskModel = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task with this id: (" + id + ") not exist"));
		taskRepository.delete(taskModel);
		Map<String, Boolean> score = new HashMap<>();
		score.put("delete", Boolean.TRUE);
		return ResponseEntity.ok(score);
	}
	
//	Partly change of task;
	@PatchMapping("/showAllTasks/{id}")
	public ResponseEntity<TaskModel> partlyChange(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		TaskModel check = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task with this id: (" + id + ") not exist"));
		
		Map<String, Function<String, Enum>> enumMap = new HashMap<>();
		enumMap.put("state", value -> TaskState.valueOf(value));
		enumMap.put("category", value -> TaskCategory.valueOf(value));
		enumMap.put("level", value -> PriorityLevel.valueOf(value));
		enumMap.put("type", value -> TaskType.valueOf(value));
		
		fields.forEach((key, value) -> {
			if(enumMap.containsKey(key)) {
				try {
					value = enumMap.get(key).apply((String) value);
				} catch (IllegalArgumentException e){
					throw new IllegalArgumentException(value + " does not match");
				}
			}
			Field field = ReflectionUtils.findField(TaskModel.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, check, value);
		});
		TaskModel success = taskRepository.save(check);
		return ResponseEntity.ok(success);
	}
}
