package org.alexsandrov.spring.integration.service;


import lombok.RequiredArgsConstructor;
import org.alexsandrov.spring.database.pool.ConnectionPool;
import org.alexsandrov.spring.integration.annotation.IntegrationTest;
import org.alexsandrov.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

@IntegrationTest
@RequiredArgsConstructor
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void test() {
        System.out.println();
    }

    @Test
    void test2() {
        System.out.println();
    }

}
