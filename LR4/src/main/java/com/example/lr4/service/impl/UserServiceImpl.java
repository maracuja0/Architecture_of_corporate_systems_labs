package com.example.lr4.service.impl;

import com.example.lr4.entity.UserEntity;
import com.example.lr4.repository.UserRepository;
import com.example.lr4.service.UserService;
import com.example.lr4.entity.ChangeEvent;
import com.example.lr4.service.jms.ChangeEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChangeEventService changeEventService;


    @Override
    public UserEntity getUser(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(UserEntity user) {
        UserEntity savedUser = userRepository.save(user);

        ChangeEvent changeEvent = ChangeEvent.builder()
                .entityType("User")
                .entityId(savedUser.getUserId())
                .action("INSERT")
                .data(Map.of(
                "userId", savedUser.getUserId(),
                "userEmail", savedUser.getUserEmail()
        )).build();

        changeEventService.send(changeEvent);

    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        boolean isNew = user.getUserId() == null;
        UserEntity savedUser = userRepository.save(user);

        ChangeEvent changeEvent = ChangeEvent.builder()
                .entityType("User")
                .entityId(savedUser.getUserId())
                .action(isNew ? "INSERT" : "UPDATE")
                .data(Map.of(
                        "userId", savedUser.getUserId(),
                        "userEmail", savedUser.getUserEmail()
                )).build();

        changeEventService.send(changeEvent);

        return savedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new RuntimeException("User with id " + userId + " not found");
        }

        userRepository.delete(user);

        ChangeEvent changeEvent = ChangeEvent.builder()
                .entityType("User")
                .entityId(userId)
                .action("DELETE")
                .data(Map.of(
                        "userId", userId,
                        "userEmail", user.getUserEmail(),
                        "userFirstName", user.getUserFirstName(),
                        "userLastName", user.getUserLastName()
                ))
                .build();

        changeEventService.send(changeEvent);
    }
}