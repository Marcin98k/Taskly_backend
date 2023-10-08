package com.example.taskly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskly.models.TaskStatusOptions;

@Repository
public interface TaskStatusOptionsRepository extends JpaRepository<TaskStatusOptions, Long>{
	
	TaskStatusOptions findByName(String name);
}
