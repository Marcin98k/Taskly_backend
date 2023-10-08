package com.example.taskly.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.taskly.models.TaskModel;

@Configuration
public class AppConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.typeMap(TaskModel.class, TaskModel.class).addMappings(mapper ->
		mapper.skip(TaskModel::setId));
		return modelMapper;
	}
}
