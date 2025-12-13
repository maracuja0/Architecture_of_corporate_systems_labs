package com.example.lr4.service;

import com.example.lr4.entity.PositionEntity;

import java.util.List;

public interface LikedService {

    void like(Long userId, Long posId);

    void dislike(Long userId, Long posId);

    List<PositionEntity> getLikedPositions(Long userId);

    List<Long> getLikedPositionIds(Long userId);
}
