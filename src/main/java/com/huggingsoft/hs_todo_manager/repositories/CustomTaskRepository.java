package com.huggingsoft.hs_todo_manager.repositories;

import com.huggingsoft.hs_todo_manager.models.entities.TaskEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface CustomTaskRepository {
    Specification<TaskEntity> filter(Map<String, String> filters);
}
