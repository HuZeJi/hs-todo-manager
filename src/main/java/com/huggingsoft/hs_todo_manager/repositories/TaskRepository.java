package com.huggingsoft.hs_todo_manager.repositories;

import com.huggingsoft.hs_todo_manager.models.entities.TaskEntity;
import com.huggingsoft.hs_todo_manager.services.uitls.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
public interface TaskRepository
        extends JpaRepository<TaskEntity, UUID>,
                JpaSpecificationExecutor<TaskEntity>,
                CustomTaskRepository
{
    default Page<TaskEntity> get(Map<String, String> filters) {
        Pageable filterPage = PageUtil.buildPageRequest(filters);
        return get(filters, filterPage);
    }

    default Page<TaskEntity> get(Map<String, String> filters, Pageable page) {
        return findAll(filter(filters), page);
    }
}
