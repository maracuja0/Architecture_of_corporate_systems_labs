package com.example.lr3.service;

import com.example.lr3.entity.PositionEntity;

import java.util.List;

public interface LikedService {

    void like(Long userId, Long posId);

    void dislike(Long userId, Long posId);

    List<PositionEntity> getLikedPositions(Long userId);

    List<Long> getLikedPositionIds(Long userId);
}
