package org.alexsandrov.spring.service;

import org.alexsandrov.spring.database.repository.CompanyRepository;
import org.alexsandrov.spring.database.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public UserService(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}
