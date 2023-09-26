package com.example.taskly.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="task_type_options")
public class TaskTypeOptions extends BaseOptions{

}
