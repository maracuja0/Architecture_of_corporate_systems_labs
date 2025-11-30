package com.example.lr2.service.impl;

import com.example.lr2.entity.PositionEntity;
import com.example.lr2.repository.PositionRepository;
import com.example.lr2.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return positionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Position not found: " + id));
    }

    @Override
    public PositionEntity save(PositionEntity position) {
        return positionRepository.save(position);
    }
}
