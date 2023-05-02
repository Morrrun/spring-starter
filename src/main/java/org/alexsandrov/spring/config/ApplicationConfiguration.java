package org.alexsandrov.spring.config;

import org.alexsandrov.spring.database.pool.ConnectionPool;
import org.alexsandrov.spring.database.repository.UserRepository;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {

        @Bean("pool2")
        @Scope(BeanDefinition.SCOPE_SINGLETON)
        public ConnectionPool pool2() {
                return new ConnectionPool("test-name", 12);
        }

        @Bean
        public ConnectionPool pool3() {
                return new ConnectionPool("test-pool", 25);
        }

        @Bean
//        @Profile("prod|web")
        public UserRepository userRepository2(ConnectionPool pool2) {
                return new UserRepository(pool2);
        }

        @Bean
        public UserRepository userRepository3(ConnectionPool pool2) {
                return new UserRepository(pool2);
        }
}
