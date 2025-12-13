package com.example.lr4.service.impl;

import com.example.lr4.entity.LikedEntity;
import com.example.lr4.entity.PositionEntity;
import com.example.lr4.entity.UserEntity;
import com.example.lr4.repository.LikedRepository;
import com.example.lr4.repository.PositionRepository;
import com.example.lr4.repository.UserRepository;
import com.example.lr4.service.LikedService;
import com.example.lr4.entity.ChangeEvent;
import com.example.lr4.service.jms.ChangeEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LikedServiceImpl implements LikedService {

    @Autowired
    private LikedRepository likedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ChangeEventService changeEventService;

    @Override
    public void like(Long userId, Long posId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        PositionEntity position = positionRepository.findById(posId)
                .orElseThrow(() -> new IllegalArgumentException("Position not found: " + posId));

        if (likedRepository.existsByUserEntityAndPositionEntity(user, position)) {
            return;
        }

        LikedEntity liked = new LikedEntity();
        liked.setUserEntity(user);
        liked.setPositionEntity(position);
        liked.setLikedTime(new Date());

        likedRepository.save(liked);

        ChangeEvent changeEvent = ChangeEvent.builder()
                .entityType("Like")
                .entityId(liked.getId())
                .action("INSERT")
                .data(Map.of("userId", userId, "positionId", posId, "time", liked.getLikedTime().toString()))
                .build();

        changeEventService.send(changeEvent);
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

            ChangeEvent changeEvent = ChangeEvent.builder()
                    .entityType("Like")
                    .entityId(liked.getId())
                    .action("DELETE")
                    .data(Map.of("userId", userId, "positionId", posId))
                    .build();

            changeEventService.send(changeEvent);
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
