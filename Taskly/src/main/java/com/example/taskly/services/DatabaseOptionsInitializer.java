package com.example.taskly.services;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.example.taskly.models.TaskCategoryOptions;
import com.example.taskly.models.TaskPriorityOptions;
import com.example.taskly.models.TaskStateOptions;
import com.example.taskly.models.TaskTypeOptions;
import com.example.taskly.repositories.TaskCategoryRepository;
import com.example.taskly.repositories.TaskPriorityRepository;
import com.example.taskly.repositories.TaskStateRepository;
import com.example.taskly.repositories.TaskTypeRepository;

@Service
public class DatabaseOptionsInitializer implements CommandLineRunner{

	private final TaskCategoryRepository taskCategoryRepository;
	private final TaskPriorityRepository taskPriorityRepository;
	private final TaskStateRepository taskStateRepository;
	private final TaskTypeRepository taskTypeRepository;
	
	public DatabaseOptionsInitializer(TaskCategoryRepository taskCategoryRepository,
			TaskPriorityRepository taskPriorityRepository, TaskStateRepository taskStateRepository,
			TaskTypeRepository taskTypeRepository) {
		this.taskCategoryRepository = taskCategoryRepository;
		this.taskPriorityRepository = taskPriorityRepository;
		this.taskStateRepository = taskStateRepository;
		this.taskTypeRepository = taskTypeRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		String[][] category = {{"WORK", "Work"}, {"HOME", "Home"}, {"ELSE", "Else"}};
		String[][] priority = {{"LOW", "Low"}, {"MEDIUM", "Medium"}, {"HIGH", "High"}};
		String[][] state = {{"ACTIVE","Active"}, {"INACTIVE", "Inactive"}, {"STOPPED", "Stopped"}, {"WAITING", "Waiting"}, {"FINISHED", "Finished"}};
		String[][] type = {{"SINGLE", "Single"}, {"REPEATED", "Repeated"}};
		
		for(String[] pair: category) {
			List<TaskCategoryOptions> foundCategorie =
					taskCategoryRepository.findByName(pair[0]);
			if(foundCategorie == null || foundCategorie.isEmpty()) {
				TaskCategoryOptions taskCategory = new TaskCategoryOptions();
				taskCategory.setName(pair[0]);
				taskCategory.setValue(pair[1]);
				taskCategoryRepository.save(taskCategory);
				}
		}
		
		for(String[] pair: priority) {
			List<TaskPriorityOptions> foundPriority =
					taskPriorityRepository.findByName(pair[0]);
			if(foundPriority == null || foundPriority.isEmpty()) {
				TaskPriorityOptions taskPriority = new TaskPriorityOptions();
				taskPriority.setName(pair[0]);
				taskPriority.setValue(pair[1]);
				taskPriorityRepository.save(taskPriority);
			}
		}
		
		for(String[] pair: state) {
			List<TaskStateOptions> foundState =
					taskStateRepository.findByName(pair[0]);
			if(foundState == null || foundState.isEmpty()) {
				TaskStateOptions taskState = new TaskStateOptions();
				taskState.setName(pair[0]);
				taskState.setValue(pair[1]);
				taskStateRepository.save(taskState);
			}
		}
		
		for(String[] pair: type) {
			List<TaskTypeOptions> foundType =
					taskTypeRepository.findByName(pair[0]);
			if(foundType == null || foundType.isEmpty()) {
				TaskTypeOptions taskType = new TaskTypeOptions();
				taskType.setName(pair[0]);
				taskType.setValue(pair[1]);
				taskTypeRepository.save(taskType);
			}
		}
	}

}
