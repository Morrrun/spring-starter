package org.alexsandrov.spring.integration;

import org.alexsandrov.spring.integration.annotation.IntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

@Sql("classpath:sql/data.sql")
@IntegrationTest
public abstract class IntegrationTestBase {
    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
//        registry.add("redis.port", redis::getMappedPort);
    }
}
