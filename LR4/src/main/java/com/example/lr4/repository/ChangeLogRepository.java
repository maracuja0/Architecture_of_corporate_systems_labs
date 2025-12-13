package com.example.lr4.repository;

import com.example.lr4.entity.ChangeLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeLogRepository extends JpaRepository<ChangeLogEntity, Long> {}
