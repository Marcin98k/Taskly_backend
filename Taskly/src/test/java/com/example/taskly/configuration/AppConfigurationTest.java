package com.example.taskly.configuration;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.example.taskly.dto.OptionsDto;
import com.example.taskly.dto.TaskDto;
import com.example.taskly.models.TaskCategoryOptions;
import com.example.taskly.models.TaskModel;
import com.example.taskly.models.TaskPriorityOptions;
import com.example.taskly.models.TaskStatusOptions;
import com.example.taskly.models.TaskTypeOptions;
import com.example.taskly.repositories.TaskStatusOptionsRepository;

class AppConfigurationTest {

	@Mock
	TaskStatusOptionsRepository taskStatusOptionsRepository;
	
	private static final long ID = 1L;
	private static final String ACTIVE = "ACTIVE";
	private static final String SINGLE = "SINGLE";
	private static final String LOW = "LOW";
	private static final String ELSE = "ELSE";
	private static final String NAME = "name";
	private static final String NOTE = "note";
	private static final String DATE_ADDED = "2020-09-12T23:00:00";
	private static final String TASK_DATE= "2020-09-15T23:00:00";
	
	private ModelMapper modelMapper = new ModelMapper();
	
	private AutoCloseable closeable;
	
	@BeforeEach
	void init() throws Exception {
		closeable =  MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	void cleanUp() throws Exception{
		closeable.close();
	}
	@Test
	void whenConvertPostEntityToPostDto_thenCorrect() {
		TaskModel taskModel = createTaskModel();
		TaskDto expectedTaskDto = createExpectedTaskDto();
		
		TaskDto actualTaskDto = modelMapper.map(taskModel, TaskDto.class);
		
		assertEquals(expectedTaskDto, actualTaskDto);
	}
	
	private TaskDto createExpectedTaskDto() {
		TaskDto taskDto = new TaskDto();
		taskDto.setUserId(ID);
		taskDto.setName(NAME);
		taskDto.setDateAdded(LocalDateTime.parse(DATE_ADDED));
		taskDto.setTaskDate(LocalDateTime.parse(TASK_DATE));
		taskDto.setStatus(new OptionsDto(ACTIVE, ACTIVE.toLowerCase()));
		taskDto.setType(new OptionsDto(SINGLE, SINGLE.toLowerCase()));
		taskDto.setPriority(new OptionsDto(LOW, LOW.toLowerCase()));
		taskDto.setCategory(new OptionsDto(ELSE, ELSE.toLowerCase()));
		taskDto.setNote(NOTE);
		return taskDto;
	}
	
	private TaskModel createTaskModel() {
		TaskModel taskModel = new TaskModel();
		taskModel.setUserId(ID);
		taskModel.setName(NAME);
		taskModel.setDateAdded(LocalDateTime.parse(DATE_ADDED));
		taskModel.setTaskDate(LocalDateTime.parse(TASK_DATE));
		taskModel.setStatus(createStatusOptions());
		taskModel.setType(createTypeOptions());
		taskModel.setPriority(createPriorityOptions());
		taskModel.setCategory(createCategoryOptions());
		taskModel.setNote(NOTE);
		return taskModel;
	}
	private TaskStatusOptions createStatusOptions() {
		TaskStatusOptions status = new TaskStatusOptions();
		status.setId(ID);
		status.setName(ACTIVE);
		status.setValue(ACTIVE.toLowerCase());
		return status;
	}
	
	private TaskTypeOptions createTypeOptions() {
		TaskTypeOptions type = new TaskTypeOptions();
		type.setId(ID);
		type.setName(SINGLE);
		type.setValue(SINGLE.toLowerCase());
		return type;
	}
	
	private TaskPriorityOptions createPriorityOptions() {
		TaskPriorityOptions priority = new TaskPriorityOptions();
		priority.setId(ID);
		priority.setName(LOW);
		priority.setValue(LOW.toLowerCase());
		return priority;
	}
	
	private TaskCategoryOptions createCategoryOptions() {
		TaskCategoryOptions category = new TaskCategoryOptions();
		category.setId(ID);
		category.setName(ELSE);
		category.setValue(ELSE.toLowerCase());
		return category;
	}
}
