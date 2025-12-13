package com.example.lr4.service.impl;

import com.example.lr4.entity.PositionEntity;
import com.example.lr4.repository.PositionRepository;
import com.example.lr4.service.PositionService;
import com.example.lr4.entity.ChangeEvent;
import com.example.lr4.service.jms.ChangeEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<PositionEntity> getAllPositions() {
        return positionRepository.findAll();
    }

    @Override
    public PositionEntity getPositionById(Long id) {
        return positionRepository.findByPositionId(id);
    }

    @Autowired
    private ChangeEventService changeEventService;

    @Override
    public PositionEntity save(PositionEntity position) {
        boolean isNew = position.getPositionId() == null;
        PositionEntity savedPosition = positionRepository.save(position);

        ChangeEvent changeEvent = ChangeEvent.builder()
                .entityType("Position")
                .entityId(savedPosition.getPositionId())
                .action(isNew ? "INSERT" : "UPDATE")
                .data(Map.of("position_id", position.getPositionId(), "position_name", position.getPositionName()))
                .build();


        changeEventService.send(changeEvent);

        return savedPosition;
    }
}
