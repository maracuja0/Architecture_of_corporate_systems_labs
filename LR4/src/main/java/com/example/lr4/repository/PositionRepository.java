package com.example.lr4.repository;

import com.example.lr4.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    PositionEntity findByPositionId(Long positionId);
}