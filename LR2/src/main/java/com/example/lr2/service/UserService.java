package com.example.lr2.service;

import com.example.lr2.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity getUser(Long userId);

    List<UserEntity> getAllUsers();

    UserEntity addUser(UserEntity user);

    UserEntity saveUser(UserEntity user);
}