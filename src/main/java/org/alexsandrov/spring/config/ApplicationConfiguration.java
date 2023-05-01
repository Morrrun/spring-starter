package org.alexsandrov.spring.config;

import org.alexsandrov.spring.database.entity.Company;
import org.alexsandrov.spring.database.pool.ConnectionPool;
import org.alexsandrov.spring.database.repository.CrudRepository;
import org.alexsandrov.spring.database.repository.UserRepository;
import org.alexsandrov.web.WebConfiguration;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:properties.properties")
@ComponentScan(basePackages = "org.alexsandrov.spring",
        useDefaultFilters = false,
        includeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class),
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
            @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
        })
//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
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
