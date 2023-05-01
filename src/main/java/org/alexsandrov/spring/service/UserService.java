package org.alexsandrov.spring.service;

import org.alexsandrov.spring.database.entity.Company;
import org.alexsandrov.spring.database.repository.CompanyRepository;
import org.alexsandrov.spring.database.repository.CrudRepository;
import org.alexsandrov.spring.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserService {
    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

    public UserService(@Qualifier("userRepository3") UserRepository userRepository, CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}
