package com.example.taskly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.taskly.models.TaskTypeOptions;

@Repository
public interface TaskTypeOptionsRepository extends JpaRepository<TaskTypeOptions, Long>{
	TaskTypeOptions findByName(String name);
}
