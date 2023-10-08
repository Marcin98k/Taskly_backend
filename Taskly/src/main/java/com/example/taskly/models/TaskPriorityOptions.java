package com.example.taskly.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="task_priority_options")
public class TaskPriorityOptions extends BaseOptions {

}
