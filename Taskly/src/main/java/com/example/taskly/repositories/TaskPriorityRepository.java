package com.example.taskly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskly.models.TaskPriorityOptions;

@Repository
public interface TaskPriorityRepository extends JpaRepository<TaskPriorityOptions, Long>{
	List<TaskPriorityOptions> findByName(String name);
}
