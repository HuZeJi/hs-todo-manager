package com.huggingsoft.hs_todo_manager.services.impl;

import com.huggingsoft.hs_todo_manager.models.entities.OwnerEntity;
import com.huggingsoft.hs_todo_manager.repositories.OwnerRepository;
import com.huggingsoft.hs_todo_manager.services.OwnerSvc;
import org.springframework.stereotype.Service;

@Service
class OwnerSvcImpl implements OwnerSvc {
    private final OwnerRepository ownerRepository;

    public OwnerSvcImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public OwnerEntity getOrCreate(String ip) {
        return ownerRepository.findByIp(ip)
                .orElseGet(() -> ownerRepository.save(new OwnerEntity(ip)));
    }
}
