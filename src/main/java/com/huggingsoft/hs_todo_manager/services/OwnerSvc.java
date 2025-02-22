package com.huggingsoft.hs_todo_manager.services;

import com.huggingsoft.hs_todo_manager.models.entities.OwnerEntity;

public interface OwnerSvc {
    OwnerEntity getOrCreate(String ip);
}
