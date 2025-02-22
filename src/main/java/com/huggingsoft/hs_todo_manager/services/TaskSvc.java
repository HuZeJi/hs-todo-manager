package com.huggingsoft.hs_todo_manager.services;

import com.huggingsoft.hs_todo_manager.models.dto.v1.TaskDto;
import com.huggingsoft.hs_todo_manager.models.dto.v1.TaskCreationRequestDto;
import com.huggingsoft.hs_todo_manager.models.dto.v1.TaskUpdateRequestDto;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.UUID;

public interface TaskSvc {
    Page<TaskDto> getTasks(Map<String, String> filters);

    TaskDto createTask(TaskCreationRequestDto request);

    TaskDto updateTask(UUID taskId, TaskUpdateRequestDto request);

    void deleteTask(UUID taskId);
}
