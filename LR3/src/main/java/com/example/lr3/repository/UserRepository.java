package com.example.lr3.repository;
import com.example.lr3.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(Long id);
}