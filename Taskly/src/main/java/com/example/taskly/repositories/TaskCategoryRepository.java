package com.example.taskly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskly.models.TaskCategoryOptions;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategoryOptions, Long>{
	List<TaskCategoryOptions> findByName(String name);
}
