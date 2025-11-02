package com.example.lr1.service;
import com.example.lr1.repository.UserRepository;
import com.example.lr1.entity.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class UserService {
    @Inject
    private UserRepository userRepo;

    public User getUser(Long id) { return userRepo.find(id); }
    public List<User> getAllUsers() { return userRepo.findAll(); }
    public void addUser(User user) { userRepo.save(user); }
    public void deleteUser(User user) { userRepo.delete(user); }
    // и т. д.
}