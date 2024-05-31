package com.xxdannilinxx.todosimple.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxdannilinxx.todosimple.models.User;
import com.xxdannilinxx.todosimple.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public User findById(Long id) {
        try {
            Optional<User> user = this.userRepository.findById(id);

            return user.orElseThrow(() -> new RuntimeException("User not found"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to find user: " + e.getMessage());
        }
    }

    @Transactional
    public User create(User u) {
        try {
            u.setId(null);
            return this.userRepository.save(u);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create user: " + e.getMessage());
        }
    }

    @Transactional
    public User update(User u) {
        try {
            User newUser = findById(u.getId());
            newUser.setName(u.getName());
            newUser.setPassword(u.getPassword());

            return this.userRepository.save(newUser);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user: " + e.getMessage());
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            findById(id);

            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user: " + e.getMessage());
        }
    }

}
