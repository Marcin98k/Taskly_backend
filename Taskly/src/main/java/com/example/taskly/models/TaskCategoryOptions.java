package com.example.taskly.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="task_category_options")
public class TaskCategoryOptions extends BaseOptions {

}
