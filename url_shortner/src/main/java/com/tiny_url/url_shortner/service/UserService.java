package com.tiny_url.url_shortner.service;

import com.tiny_url.url_shortner.repository.UserRepository;
import com.tiny_url.url_shortner.models.User;
import com.tiny_url.url_shortner.util.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        if (user.getUniqueCode() == null) {
            user.setUniqueCode(CodeGenerator.generateUniqueCode());
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}