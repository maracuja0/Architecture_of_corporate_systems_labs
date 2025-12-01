package com.example.lr3.service.impl;

import com.example.lr3.entity.UserEntity;
import com.example.lr3.repository.UserRepository;
import com.example.lr3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity getUser(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
}