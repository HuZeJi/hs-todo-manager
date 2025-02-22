package com.huggingsoft.hs_todo_manager.models.dto.v1;

import com.huggingsoft.hs_todo_manager.models.types.Status;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

@Data
public class TaskCreationRequestDto {
    @NonNull
    private String title;
    private String description;
    private Status status;
    private LocalDate dueDate;
    private String ownerIP;
    private List<String> tags;
}
