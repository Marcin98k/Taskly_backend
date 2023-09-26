package com.example.taskly.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="task_state_options")
public class TaskStateOptions extends BaseOptions{

}
