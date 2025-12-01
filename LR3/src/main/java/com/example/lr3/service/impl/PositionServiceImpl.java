package com.example.lr3.service.impl;

import com.example.lr3.entity.PositionEntity;
import com.example.lr3.repository.PositionRepository;
import com.example.lr3.service.PositionService;
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
        return positionRepository.findByPositionId(id);
    }

    @Override
    public PositionEntity save(PositionEntity position) {
        return positionRepository.save(position);
    }
}
