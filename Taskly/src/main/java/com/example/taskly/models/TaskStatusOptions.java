package com.example.taskly.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="task_status_options")
public class TaskStatusOptions extends BaseOptions {
}
