package com.example.taskly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskly.models.TaskModel;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long>{

}
