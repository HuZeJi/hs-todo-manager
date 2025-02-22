package com.huggingsoft.hs_todo_manager.models.mappers;

import com.huggingsoft.hs_todo_manager.models.dto.v1.TaskDto;
import com.huggingsoft.hs_todo_manager.models.entities.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto toDto(TaskEntity entity);
}
