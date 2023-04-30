package org.alexsandrov.spring;

import org.alexsandrov.spring.database.pool.ConnectionPool;
import org.alexsandrov.spring.database.repository.UserRepository;
import org.alexsandrov.spring.service.UserService;

public class ApplicationRunner {
    public static void main(String[] args) {
        var connectionPool = new ConnectionPool();
        var userRepository = new UserRepository(connectionPool);
        var userService = new UserService(userRepository);
    }
}