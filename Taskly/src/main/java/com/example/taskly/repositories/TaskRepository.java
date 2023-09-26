package com.example.taskly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskly.enums.TaskCategory;
import com.example.taskly.enums.TaskState;
import com.example.taskly.models.TaskModel;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long>{

	List<TaskModel> findByUserId(Long userId);
	List<TaskModel> findByUserIdAndState(Long userId, TaskState state);
	List<TaskModel> findByUserIdAndStateNot(Long userId, TaskState state);
	List<TaskModel> findByUserIdAndCategory(Long userId, TaskCategory category);
	
	Long countByUserId(Long userId);
	Long countByUserIdAndState(Long userId, TaskState state);
	Long countByUserIdAndStateNot(Long userId, TaskState state);
}
