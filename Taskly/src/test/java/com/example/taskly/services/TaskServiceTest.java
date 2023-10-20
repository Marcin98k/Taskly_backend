package com.example.taskly.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.example.taskly.dto.TaskDto;
import com.example.taskly.models.TaskModel;
import com.example.taskly.repositories.TaskCategoryOptionsRepository;
import com.example.taskly.repositories.TaskPriorityOptionsRepository;
import com.example.taskly.repositories.TaskRepository;
import com.example.taskly.repositories.TaskStatusOptionsRepository;
import com.example.taskly.repositories.TaskTypeOptionsRepository;

class TaskServiceTest {
	
	@InjectMocks
	private TaskService taskService;
	
	@Mock
	private TaskRepository taskRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private TaskStatusOptionsRepository taskStatusOptionsRepository;
	
	@Mock
	private TaskTypeOptionsRepository taskTypeOptionsRepository;
	
	@Mock
	private TaskPriorityOptionsRepository taskPriorityOptionsRepository;
	
	@Mock
	private TaskCategoryOptionsRepository taskCategoryOptionsRepository;
	
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
	void getTaskTest() {
		
	    Long id = 1L;
	    TaskModel modelMock = Mockito.mock(TaskModel.class);
	    TaskDto dtoMock = Mockito.mock(TaskDto.class);
	    Optional<TaskModel> optionalModel = Optional.of(modelMock);

	    when(taskRepository.findById(id)).thenReturn(optionalModel);
	    when(modelMapper.map(modelMock, TaskDto.class)).thenReturn(dtoMock);
	    
	    TaskDto result = taskService.getTask(id);

	    assertEquals(dtoMock, result);
	    
	    verify(taskRepository, times(1)).findById(id);
	    verify(modelMapper, times(1)).map(modelMock, TaskDto.class);
	}
}
