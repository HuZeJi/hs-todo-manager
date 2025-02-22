package com.huggingsoft.hs_todo_manager.models.dto.v1;

import com.huggingsoft.hs_todo_manager.models.types.Status;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class TaskDto {
    private UUID id;
    private String title;
    private String description;
    private Status status;
    private LocalDate dueDate;
    private List<String> tags;
}
