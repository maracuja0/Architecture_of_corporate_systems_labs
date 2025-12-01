package com.example.lr3.repository;

import com.example.lr3.entity.LikedEntity;
import com.example.lr3.entity.PositionEntity;
import com.example.lr3.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikedRepository extends JpaRepository<LikedEntity, Long> {
    List<LikedEntity> findByUserEntity(UserEntity user);
    LikedEntity findByUserEntityAndPositionEntity(UserEntity user, PositionEntity position);
    boolean existsByUserEntityAndPositionEntity(UserEntity user, PositionEntity position);
}
