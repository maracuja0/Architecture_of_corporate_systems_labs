package com.example.lr4.service;

import com.example.lr4.entity.PositionEntity;

import java.util.List;

public interface PositionService {

    List<PositionEntity> getAllPositions();

    PositionEntity getPositionById(Long id);

    PositionEntity save(PositionEntity position);
}
