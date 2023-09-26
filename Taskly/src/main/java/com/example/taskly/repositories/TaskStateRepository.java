package com.example.taskly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskly.models.TaskStateOptions;

@Repository
public interface TaskStateRepository extends JpaRepository<TaskStateOptions, Long>{
	List<TaskStateOptions> findByName(String name);
}
