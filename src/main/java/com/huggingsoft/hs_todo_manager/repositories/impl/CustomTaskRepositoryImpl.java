package com.huggingsoft.hs_todo_manager.repositories.impl;

import com.huggingsoft.hs_todo_manager.models.entities.TaskEntity;
import com.huggingsoft.hs_todo_manager.repositories.CustomTaskRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CustomTaskRepositoryImpl implements CustomTaskRepository {
    @Override
    public Specification<TaskEntity> filter(Map<String, String> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = filters
                    .entrySet()
                    .stream()
                    .map(filter -> buildPredicate(criteriaBuilder, root, filter))
                    .toList();
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Predicate buildPredicate(
            CriteriaBuilder criteriaBuilder,
            Root<TaskEntity> root,
            Map.Entry<String, String> filter
    ) {
        Path<Object> path = null;
        for (String key : filter.getKey().split("\\.")) {
            path = (path == null) ? root.get(key) : path.get(key);
        }
        return criteriaBuilder.equal(path, filter.getValue());
    }
}
