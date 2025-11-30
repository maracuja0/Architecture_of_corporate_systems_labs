package com.example.lr2.service;

import com.example.lr2.entity.PositionEntity;
import com.example.lr2.entity.UserEntity;

import java.util.List;

public interface LikedService {

    void like(Long userId, Long posId);

    void dislike(Long userId, Long posId);

    List<PositionEntity> getLikedPositions(Long userId);

    List<Long> getLikedPositionIds(Long userId);
}
