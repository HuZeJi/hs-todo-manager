package com.huggingsoft.hs_todo_manager.web.controllers;

import com.huggingsoft.hs_todo_manager.models.dto.v1.TaskCreationRequestDto;
import com.huggingsoft.hs_todo_manager.models.dto.v1.TaskUpdateRequestDto;
import com.huggingsoft.hs_todo_manager.services.TaskSvc;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/owners/tasks")
public class TaskController {

    private final TaskSvc taskSvc;

    public TaskController(TaskSvc taskSvc) {
        this.taskSvc = taskSvc;
    }

    @GetMapping
    public ResponseEntity<?> getTask(
            @RequestParam Map<String, String> filters,
            HttpServletRequest request
    ) {
        filters.put("owner.ip", getIpAddress(request));
        return ResponseEntity.ok(taskSvc.getTasks(filters));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(
            @PathVariable("id") UUID id,
            @RequestBody TaskUpdateRequestDto request
    ) {
        return ResponseEntity.ok(taskSvc.updateTask(id, request));
    }

    @PostMapping
    public ResponseEntity<?> createTask(
            @RequestBody TaskCreationRequestDto taskRequest,
            HttpServletRequest request
    ) {
        taskRequest.setOwnerIP(getIpAddress(request));
        return ResponseEntity.ok(taskSvc.createTask(taskRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") UUID id) {
        taskSvc.deleteTask(id);
        return ResponseEntity.ok("Task successfully deleted");
    }

    // TODO: move this to another layer
    private String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        } else {
            ipAddress = ipAddress.split(",")[0];
        }
        return ipAddress;
    }
}
