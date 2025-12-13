package com.example.lr4.service;

import com.example.lr4.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity getUser(Long userId);

    List<UserEntity> getAllUsers();

    void addUser(UserEntity user);

    UserEntity saveUser(UserEntity user);

    void deleteUser(Long userId);
}