package org.alexsandrov.spring.service;

import org.alexsandrov.spring.database.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
