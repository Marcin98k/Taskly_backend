package com.example.taskly.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskly.models.TaskCategoryOptions;
import com.example.taskly.models.TaskModel;
import com.example.taskly.models.TaskStatusOptions;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long>{

	List<TaskModel> findByUserId(Long userId);
	Optional<TaskStatusOptions> findByStatusName(String option);
	List<TaskModel> findByUserIdAndStatus(Long userId, TaskStatusOptions status);
	List<TaskModel> findByUserIdAndStatusNot(Long userId, TaskStatusOptions status);
	List<TaskModel> findByUserIdAndCategory(Long userId, TaskCategoryOptions category);
	
	Long countByUserId(Long userId);
	Long countByUserIdAndStatus(Long userId, TaskStatusOptions status);
	Long countByUserIdAndStatusNot(Long userId, TaskStatusOptions status);
}
