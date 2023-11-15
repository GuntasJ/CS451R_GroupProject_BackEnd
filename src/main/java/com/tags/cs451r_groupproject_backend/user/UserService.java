package com.tags.cs451r_groupproject_backend.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

}
