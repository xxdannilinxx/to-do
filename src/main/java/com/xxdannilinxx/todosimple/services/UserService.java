package com.xxdannilinxx.todosimple.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxdannilinxx.todosimple.models.User;
import com.xxdannilinxx.todosimple.repositories.UserRepository;
import com.xxdannilinxx.todosimple.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    @Transactional
    public User create(User u) {
        u.setId(null);
        return this.userRepository.save(u);
    }

    @Transactional
    public User update(User u) {
        User newUser = findById(u.getId());
        newUser.setName(u.getName());
        newUser.setPassword(u.getPassword());

        return this.userRepository.save(newUser);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);

        this.userRepository.deleteById(id);
    }
}
