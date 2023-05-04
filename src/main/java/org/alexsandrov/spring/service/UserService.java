package org.alexsandrov.spring.service;

import lombok.RequiredArgsConstructor;
import org.alexsandrov.spring.database.repository.CompanyRepository;
import org.alexsandrov.spring.database.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;


}
