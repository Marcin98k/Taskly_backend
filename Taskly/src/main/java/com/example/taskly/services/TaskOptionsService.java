package com.example.taskly.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;
import org.springframework.stereotype.Service;

import com.example.taskly.dto.OptionsDto;
import com.example.taskly.models.TaskCategoryOptions;
import com.example.taskly.models.TaskPriorityOptions;
import com.example.taskly.models.TaskStatusOptions;
import com.example.taskly.models.TaskTypeOptions;
import com.example.taskly.repositories.TaskCategoryOptionsRepository;
import com.example.taskly.repositories.TaskPriorityOptionsRepository;
import com.example.taskly.repositories.TaskStatusOptionsRepository;
import com.example.taskly.repositories.TaskTypeOptionsRepository;

@Service
public class TaskOptionsService {
	
	private final TaskStatusOptionsRepository taskStatusOptionsRepository;
	private final TaskPriorityOptionsRepository taskPriorityOptionsRepository;
	private final TaskCategoryOptionsRepository taskCategoryOptionsRepository;
	private final TaskTypeOptionsRepository taskTypeOptionsRepository;
	private final ModelMapper modelMapper;
	
	public TaskOptionsService(TaskStatusOptionsRepository taskStatusOptionsRepository,
			TaskPriorityOptionsRepository taskPriorityOptionsRepository,
			TaskCategoryOptionsRepository taskCategoryOptionsRepository,
			TaskTypeOptionsRepository taskTypeOptionsRepository,
			ModelMapper modelMapper) {
		this.taskStatusOptionsRepository = taskStatusOptionsRepository;
		this.taskPriorityOptionsRepository = taskPriorityOptionsRepository;
		this.taskCategoryOptionsRepository = taskCategoryOptionsRepository;
		this.taskTypeOptionsRepository = taskTypeOptionsRepository;
		this.modelMapper = modelMapper;
	}

	public List<OptionsDto> getStatusOptions() {
		List<TaskStatusOptions> statusList =  taskStatusOptionsRepository.findAll();
		Type newList = new TypeToken<List<OptionsDto>>() {}.getType();
		return modelMapper.map(statusList, newList);
	}
	
	public List<OptionsDto> getPriorityOptions() {
		List<TaskPriorityOptions> priorityList =  taskPriorityOptionsRepository.findAll();
		Type newList = new TypeToken<List<OptionsDto>>() {}.getType(); 
		return modelMapper.map(priorityList, newList);
	}
	
	public List<OptionsDto> getCategoryOptions() {
		List<TaskCategoryOptions> categoryList =  taskCategoryOptionsRepository.findAll();
		Type newList = new TypeToken<List<OptionsDto>>() {}.getType(); 
		return modelMapper.map(categoryList, newList);
	}
	
	public List<OptionsDto> getTypeOptions() {
		List<TaskTypeOptions> typeList =  taskTypeOptionsRepository.findAll();
		Type newList = new TypeToken<List<OptionsDto>>() {}.getType(); 
		return modelMapper.map(typeList, newList);
	}
}
