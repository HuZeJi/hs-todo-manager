package com.huggingsoft.hs_todo_manager.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.huggingsoft.hs_todo_manager.models.dto.v1.TaskDto;
import com.huggingsoft.hs_todo_manager.models.mappers.TaskMapper;
import com.huggingsoft.hs_todo_manager.models.types.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "tasks", schema = "todo_manager")
public class TaskEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private LocalDate dueDate;
    private String description;
    private Status status;
    private List<String> tags;
    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    @JsonBackReference(value = "owner-task")
    private OwnerEntity owner;

    public TaskDto toDto() {
        return TaskMapper.INSTANCE.toDto(this);
    }
}
