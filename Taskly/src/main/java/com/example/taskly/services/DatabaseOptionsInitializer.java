package com.example.taskly.services;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

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
		String[][] category = {{"WORK", "Work"}, {"HOME", "Home"}, {"ELSE", "Else"}};
		String[][] priority = {{"LOW", "Low"}, {"MEDIUM", "Medium"}, {"HIGH", "High"}};
		String[][] status = {{"ACTIVE","Active"}, {"INACTIVE", "Inactive"}, {"STOPPED", "Stopped"}, {"WAITING", "Waiting"}, {"FINISHED", "Finished"}};
		String[][] type = {{"SINGLE", "Single"}, {"REPEATED", "Repeated"}};
		
		for(String[] pair: category) {
			TaskCategoryOptions foundCategorie =
					taskCategoryRepository.findByName(pair[0]);
			if(foundCategorie == null) {
				TaskCategoryOptions taskCategory = new TaskCategoryOptions();
				taskCategory.setName(pair[0]);
				taskCategory.setValue(pair[1]);
				taskCategoryRepository.save(taskCategory);
			}
		}
		
		for(String[] pair: priority) {
			TaskPriorityOptions foundPriority =
					taskPriorityRepository.findByName(pair[0]);
			if(foundPriority == null) {
				TaskPriorityOptions taskPriority = new TaskPriorityOptions();
				taskPriority.setName(pair[0]);
				taskPriority.setValue(pair[1]);
				taskPriorityRepository.save(taskPriority);
			}
		}
		
		for(String[] pair: status) {
			TaskStatusOptions foundState =
					taskStateRepository.findByName(pair[0]);
			if(foundState == null) {
				TaskStatusOptions taskState = new TaskStatusOptions();
				taskState.setName(pair[0]);
				taskState.setValue(pair[1]);
				taskStateRepository.save(taskState);
			}
		}
		
		for(String[] pair: type) {
			TaskTypeOptions foundType =
					taskTypeRepository.findByName(pair[0]);
			if(foundType == null) {
				TaskTypeOptions taskType = new TaskTypeOptions();
				taskType.setName(pair[0]);
				taskType.setValue(pair[1]);
				taskTypeRepository.save(taskType);
			}
		}
	}

}
