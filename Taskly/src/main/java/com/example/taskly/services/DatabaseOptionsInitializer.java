package com.example.taskly.services;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.example.taskly.enums.TaskCategory;
import com.example.taskly.enums.TaskPriority;
import com.example.taskly.enums.TaskStatus;
import com.example.taskly.enums.TaskType;
import com.example.taskly.models.TaskCategoryOptions;
import com.example.taskly.models.TaskPriorityOptions;
import com.example.taskly.models.TaskStatusOptions;
import com.example.taskly.models.TaskTypeOptions;
import com.example.taskly.repositories.TaskCategoryOptionsRepository;
import com.example.taskly.repositories.TaskPriorityOptionsRepository;
import com.example.taskly.repositories.TaskStatusOptionsRepository;
import com.example.taskly.repositories.TaskTypeOptionsRepository;

@Service
public class DatabaseOptionsInitializer implements CommandLineRunner{

	private final TaskCategoryOptionsRepository taskCategoryRepository;
	private final TaskPriorityOptionsRepository taskPriorityRepository;
	private final TaskStatusOptionsRepository taskStateRepository;
	private final TaskTypeOptionsRepository taskTypeRepository;
	
	public DatabaseOptionsInitializer(TaskCategoryOptionsRepository taskCategoryRepository,
			TaskPriorityOptionsRepository taskPriorityRepository, TaskStatusOptionsRepository taskStateRepository,
			TaskTypeOptionsRepository taskTypeRepository) {
		this.taskCategoryRepository = taskCategoryRepository;
		this.taskPriorityRepository = taskPriorityRepository;
		this.taskStateRepository = taskStateRepository;
		this.taskTypeRepository = taskTypeRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Map<TaskCategory, String> categoryOptions = TaskCategory.getCategoryList();
		Map<TaskPriority, String> priorityOptions = TaskPriority.getPriorityList();
		Map<TaskStatus, String> statusOptions = TaskStatus.getStatusList();
		Map<TaskType, String> typeOptions = TaskType.getTypeList();
		
		initialzeCategoryTable(categoryOptions);
		initializePriorityTable(priorityOptions);
		initializeStatusTable(statusOptions);
		initializeTypeTable(typeOptions);
	}
	
	private void initialzeCategoryTable(Map<TaskCategory, String> categoryOptions) {
		categoryOptions.forEach((key, value) -> {
			TaskCategoryOptions foundCategorie = taskCategoryRepository.findByName(key.toString());
			if(foundCategorie == null) {
				TaskCategoryOptions taskCategory = new TaskCategoryOptions();
				taskCategory.setName(key.toString());
				taskCategory.setValue(value);
				taskCategoryRepository.save(taskCategory);
			}
		});
	}
	
	private void initializePriorityTable(Map<TaskPriority, String> priorityOptions) {
		priorityOptions.forEach((key, value) -> {
			TaskPriorityOptions foundPriority = taskPriorityRepository.findByName(key.toString());
			if(foundPriority == null) {
				TaskPriorityOptions taskPriority = new TaskPriorityOptions();
				taskPriority.setName(key.toString());
				taskPriority.setValue(value);
				taskPriorityRepository.save(taskPriority);
			}
		});
	}
	
	private void initializeStatusTable(Map<TaskStatus, String> statusOptions) {
		statusOptions.forEach((key, value) -> {
			TaskStatusOptions foundState = taskStateRepository.findByName(key.toString());
			if(foundState == null) {
				TaskStatusOptions taskState = new TaskStatusOptions();
				taskState.setName(key.toString());
				taskState.setValue(value);
				taskStateRepository.save(taskState);
			}
		});
	}
	
	private void initializeTypeTable(Map<TaskType, String> typeOptions) {
		typeOptions.forEach((key, value) -> {
			TaskTypeOptions foundType = taskTypeRepository.findByName(key.toString());
			if(foundType == null) {
				TaskTypeOptions taskType = new TaskTypeOptions();
				taskType.setName(key.toString());
				taskType.setValue(value);
				taskTypeRepository.save(taskType);
			}
		});
	}
}
