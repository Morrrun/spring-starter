package org.alexsandrov.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.alexsandrov.spring.database.entity.User;
import org.alexsandrov.spring.database.repository.UserRepository;
import org.alexsandrov.spring.integration.annotation.IntegrationTest;
import org.junit.jupiter.api.Test;

import java.util.List;


@IntegrationTest
@RequiredArgsConstructor
class UserRepositoryIT {
private final UserRepository userRepository;
    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a", "ov");



    }
}