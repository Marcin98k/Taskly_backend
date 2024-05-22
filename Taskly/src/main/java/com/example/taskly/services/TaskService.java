package com.example.taskly.services;

import java.lang.reflect.Field;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.taskly.dto.OptionsDto;
import com.example.taskly.dto.TaskDto;
import com.example.taskly.enums.TaskPriority;
import com.example.taskly.enums.TaskCategory;
import com.example.taskly.enums.TaskStatus;
import com.example.taskly.enums.TaskType;
import com.example.taskly.exceptions.ResourceNotFoundException;
import com.example.taskly.models.ApplicationUser;
import com.example.taskly.models.TaskCategoryOptions;
import com.example.taskly.models.TaskModel;
import com.example.taskly.models.TaskPriorityOptions;
import com.example.taskly.models.TaskStatusOptions;
import com.example.taskly.models.TaskTypeOptions;
import com.example.taskly.repositories.TaskCategoryOptionsRepository;
import com.example.taskly.repositories.TaskPriorityOptionsRepository;
import com.example.taskly.repositories.TaskRepository;
import com.example.taskly.repositories.TaskStatusOptionsRepository;
import com.example.taskly.repositories.TaskTypeOptionsRepository;
import com.example.taskly.responses.ApiResponseDto;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final ModelMapper modelMapper;
	private final TaskStatusOptionsRepository taskStatusOptionsRepository;
	private final TaskTypeOptionsRepository taskTypeOptionsRepository;
	private final TaskPriorityOptionsRepository taskPriorityOptionsRepository;
	private final TaskCategoryOptionsRepository taskCategoryOptionsRepository;
	
	public TaskService(TaskRepository taskRepository, ModelMapper modelMapper,
			TaskStatusOptionsRepository taskStatusOptionsRepository,
			TaskTypeOptionsRepository taskTypeOptionsRepository,
			TaskPriorityOptionsRepository taskPriorityOptionsRepository,
			TaskCategoryOptionsRepository taskCategoryOptionsRepository) {
		this.taskRepository = taskRepository;
		this.modelMapper = modelMapper;
		this.taskStatusOptionsRepository = taskStatusOptionsRepository;
		this.taskTypeOptionsRepository = taskTypeOptionsRepository;
		this.taskPriorityOptionsRepository = taskPriorityOptionsRepository;
		this.taskCategoryOptionsRepository = taskCategoryOptionsRepository;
	}
	
	private TaskDto convertModelToDto(@RequestBody TaskModel taskModel) {
		return modelMapper.map(taskModel, TaskDto.class);
	}

	private TaskModel convertDtoToModel(@RequestBody TaskDto taskDto) {

		TaskStatusOptions status = getStatus(taskDto.getStatus());
	    TaskCategoryOptions category = getCategory(taskDto.getCategory());
	    TaskPriorityOptions priority = getPriority(taskDto.getPriority());
	    TaskTypeOptions type = getType(taskDto.getType());
	    
	    TaskModel taskModel = modelMapper.map(taskDto, TaskModel.class);

	    taskModel.setStatus(status);
	    taskModel.setCategory(category);
	    taskModel.setPriority(priority);
	    taskModel.setType(type);

	    return taskModel;
	}
	
	private TaskStatusOptions getStatus(String name) {
	    TaskStatusOptions statusOptions = taskStatusOptionsRepository.findByName(name);
	    if (statusOptions == null) {
	    	return taskStatusOptionsRepository.findByName("INACTIVE");
	    } else {
	        return statusOptions;
	    }
	}
	
	private TaskStatusOptions getStatus(OptionsDto optionsDto) {
		String name = optionsDto.getName();
		return getStatus(name);
	}

	private TaskCategoryOptions getCategory(String name) {
	    TaskCategoryOptions categoryOptions = taskCategoryOptionsRepository.findByName(name);
	    if (categoryOptions == null) {
	    	return taskCategoryOptionsRepository.findByName("ELSE");
	    } else {
	        return categoryOptions;
	    }
	}
	
	private TaskCategoryOptions getCategory(OptionsDto optionsDto) {
		String name = optionsDto.getName();
	    return getCategory(name);
	}

	private TaskPriorityOptions getPriority(String name) {
	    TaskPriorityOptions priorityOptions = taskPriorityOptionsRepository.findByName(name);
	    if (priorityOptions == null) {
	    	return taskPriorityOptionsRepository.findByName("LOW");
	    } else {
	        return priorityOptions;
	    }
	}
	
	private TaskPriorityOptions getPriority(OptionsDto optionsDto) {
		String name = optionsDto.getName();
	    return getPriority(name);
	}

	private TaskTypeOptions getType(String name) {
	    TaskTypeOptions typeOptions = taskTypeOptionsRepository.findByName(name);
	    if (typeOptions == null) {
	    	return taskTypeOptionsRepository.findByName("SINGLE");
	    } else {
	        return typeOptions;
	    }
	}
	
	private TaskTypeOptions getType(OptionsDto optionsDto) {
		String name = optionsDto.getName();
	    return getType(name);
	}
	
	public TaskDto createTask(@RequestBody TaskDto taskDto) {
		TaskModel taskModel = convertDtoToModel(taskDto);
		TaskModel savedTask = taskRepository.save(taskModel);
		TaskDto savedTaskDto = convertModelToDto(savedTask);
		return savedTaskDto;
	}
	
	public ResponseEntity<ApiResponseDto> updateTask(Long id, TaskModel taskModelDetails) {
		TaskModel taskModel = findTaskById(id);
		modelMapper.map(taskModelDetails, taskModel);
		taskRepository.save(taskModel);
		ApiResponseDto response = new ApiResponseDto(true, "Task updated successfully");
		return ResponseEntity.ok(response);
	}
	
	public ResponseEntity<ApiResponseDto> partllyUpdateTask(Long id, Map<String, Object> fields) {
		TaskModel check = findTaskById(id);
		
		Map<String, Function<String, Object>> optionsMap = new HashMap<>();
		optionsMap.put("status", value -> getStatus(value));
		optionsMap.put("priority", value -> getPriority(value));
		optionsMap.put("category", value -> getCategory(value));
		optionsMap.put("type", value -> getType(value));

		fields.forEach((key, value) -> {
			if (optionsMap.containsKey(key)) {
				try {
					value = optionsMap.get(key).apply((String) value);
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException(value + " does not match");
				}
			}
			Field field = ReflectionUtils.findField(TaskModel.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, check, value);
		});
		taskRepository.save(check);
		ApiResponseDto response = new ApiResponseDto(true, "Task updated successfully");
		return ResponseEntity.ok(response);
	}
	
	public ResponseEntity<ApiResponseDto> deleteTask(Long id) {
		  TaskModel taskModel = findTaskById(id);
		  taskRepository.delete(taskModel);
		  ApiResponseDto response = new ApiResponseDto(true, "Task deleted successfully");
		  return ResponseEntity.ok(response);
	}
	
	public TaskDto getTask(Long id) {
		Optional<TaskModel> taskFormDb = taskRepository.findById(id);
		TaskModel model = taskFormDb.get();
		TaskDto convertedTask = modelMapper.map(model, TaskDto.class);
		return convertedTask;
	}
	
	public List<TaskDto> getAllTasks() {
		List<TaskModel> taskModels = taskRepository.findAll();
	    Type listType = new TypeToken<List<TaskDto>>() {}.getType();
	    List<TaskDto> taskDtos = modelMapper.map(taskModels, listType);
	    return taskDtos;
	}
	
	public List<TaskDto> getUserTask(Long userId) {
		return taskRepository.findByUserId(userId).stream()
				.map(this::convertModelToDto)
				.collect(Collectors.toList());
	}
	
	public List<TaskDto> getFinishedTask(Long userId) {
		return taskRepository.findByUserIdAndStatus(userId, getStatus("FINISHED")).stream()
				.map(this::convertModelToDto)
				.collect(Collectors.toList());
		}

	public List<TaskDto> getCurrentTask(Long userId) {
		return taskRepository.findByUserIdAndStatusNot(userId, getStatus("FINISHED")).stream()
				.map(this::convertModelToDto)
				.collect(Collectors.toList());
		}
	
	public List<TaskDto> getTaskByCategory(Long userId, String category) {
	return taskRepository.findByUserIdAndCategory(userId, getCategory(category)).stream()
			.map(this::convertModelToDto)
			.collect(Collectors.toList());
	}
	
	public Long getAllTaskCount(Long userId) {
		return taskRepository.countByUserId(userId);
	}
	
	public Long getCompletedTaskCount(Long userId) {
		return taskRepository.countByUserIdAndStatus(userId, getStatus("FINISHED"));
	}
	 
	public Long getActiveCount(Long userId) {
		return taskRepository.countByUserIdAndStatusNot(userId, getStatus("FINISHED"));
	}
	
	private TaskModel findTaskById(Long id) {
		return taskRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Task with this id: (" + id + ") not exist"));
	}
}
