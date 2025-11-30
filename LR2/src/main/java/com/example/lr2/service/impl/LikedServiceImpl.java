package com.example.lr2.service.impl;

import com.example.lr2.entity.LikedEntity;
import com.example.lr2.entity.PositionEntity;
import com.example.lr2.entity.UserEntity;
import com.example.lr2.repository.LikedRepository;
import com.example.lr2.repository.PositionRepository;
import com.example.lr2.repository.UserRepository;
import com.example.lr2.service.LikedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikedServiceImpl implements LikedService {

    @Autowired
    private LikedRepository likedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public void like(Long userId, Long posId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        PositionEntity position = positionRepository.findById(posId)
                .orElseThrow(() -> new IllegalArgumentException("Position not found: " + posId));

        // Проверяем, лайкал ли уже пользователь
        if (likedRepository.existsByUserEntityAndPositionEntity(user, position)) {
            return; // лайк уже есть, ничего не делаем
        }

        LikedEntity liked = new LikedEntity();
        liked.setUserEntity(user);
        liked.setPositionEntity(position);
        liked.setLikedTime(new Date());

        likedRepository.save(liked);
    }

    @Override
    public void dislike(Long userId, Long posId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        PositionEntity position = positionRepository.findById(posId)
                .orElseThrow(() -> new IllegalArgumentException("Position not found: " + posId));

        LikedEntity liked = likedRepository.findByUserEntityAndPositionEntity(user, position);
        if (liked != null) {
            likedRepository.delete(liked);
        }
    }

    @Override
    public List<PositionEntity> getLikedPositions(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        return likedRepository.findByUserEntity(user)
                .stream()
                .map(LikedEntity::getPositionEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getLikedPositionIds(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        return likedRepository.findByUserEntity(user)
                .stream()
                .map(like -> like.getPositionEntity().getPositionId())
                .collect(Collectors.toList());
    }
}
