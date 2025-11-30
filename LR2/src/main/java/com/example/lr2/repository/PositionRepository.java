package com.example.lr2.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lr2.entity.PositionEntity;

import java.util.List;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> { }