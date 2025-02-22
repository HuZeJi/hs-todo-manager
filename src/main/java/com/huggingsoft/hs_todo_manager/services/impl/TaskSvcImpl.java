package com.huggingsoft.hs_todo_manager.services.impl;

import com.huggingsoft.hs_todo_manager.models.dto.v1.TaskCreationRequestDto;
import com.huggingsoft.hs_todo_manager.models.dto.v1.TaskDto;
import com.huggingsoft.hs_todo_manager.models.dto.v1.TaskUpdateRequestDto;
import com.huggingsoft.hs_todo_manager.models.entities.TaskEntity;
import com.huggingsoft.hs_todo_manager.models.types.Status;
import com.huggingsoft.hs_todo_manager.repositories.TaskRepository;
import com.huggingsoft.hs_todo_manager.services.TaskSvc;
import com.huggingsoft.hs_todo_manager.services.OwnerSvc;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskSvcImpl implements TaskSvc {
    private final TaskRepository taskRepository;
    private final OwnerSvc ownerSvc;

    public TaskSvcImpl(TaskRepository taskRepository, OwnerSvc ownerSvc) {
        this.taskRepository = taskRepository;
        this.ownerSvc = ownerSvc;
    }

    @Override
    public Page<TaskDto> getTasks(Map<String, String> filters) {

        return Optional
                .ofNullable(taskRepository.get(filters))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No tasks found"))
                .map(TaskEntity::toDto);
    }

    private Map<String, String> cleanFilter(Map<String, String> filters) {
        // TODO: remove all the possible filters that are not needed
        return filters;
    }

    @Override
    public TaskDto createTask(TaskCreationRequestDto request) {
        // TODO: add validation to create tasks
        TaskEntity task = new TaskEntity();
        task.setOwner(ownerSvc.getOrCreate(request.getOwnerIP()));

        Status status = Optional.ofNullable(request.getStatus()).orElse(Status.PENDING);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(status);
        task.setDueDate(request.getDueDate());
        task.setTags(request.getTags());
        return taskRepository.save(task).toDto();
    }

    @Override
    public TaskDto updateTask(UUID taskId, TaskUpdateRequestDto request) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        Optional.ofNullable(request.getTitle()).ifPresent(task::setTitle);
        Optional.ofNullable(request.getDescription()).ifPresent(task::setDescription);
        Optional.ofNullable(request.getStatus()).ifPresent(task::setStatus);
        Optional.ofNullable(request.getDueDate()).ifPresent(task::setDueDate);
        Optional.ofNullable(request.getTags()).ifPresent(task::setTags);
        return taskRepository.save(task).toDto();
    }

    @Override
    public void deleteTask(UUID taskId) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        task.setStatus(Status.CANCELLED);
        taskRepository.save(task);
    }
}
