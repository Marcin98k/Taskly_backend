package com.example.taskly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskly.models.TaskPriorityOptions;

@Repository
public interface TaskPriorityOptionsRepository extends JpaRepository<TaskPriorityOptions, Long>{
	TaskPriorityOptions findByName(String name);
}
