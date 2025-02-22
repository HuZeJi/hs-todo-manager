package com.huggingsoft.hs_todo_manager.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "owners", schema = "todo_manager")
@NoArgsConstructor
public class OwnerEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String ip;
    @OneToMany(mappedBy = "owner")
    @JsonManagedReference(value = "owner-task")
    private List<TaskEntity> tasks;

    public OwnerEntity(String ip) {
        this.ip = ip;
    }
}
