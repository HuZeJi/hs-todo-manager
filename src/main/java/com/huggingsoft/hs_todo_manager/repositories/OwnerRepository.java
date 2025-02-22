package com.huggingsoft.hs_todo_manager.repositories;

import com.huggingsoft.hs_todo_manager.models.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, UUID> {
    Optional<OwnerEntity> findByIp(String ip);
}
