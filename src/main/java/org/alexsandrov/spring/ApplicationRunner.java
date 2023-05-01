package org.alexsandrov.spring;

import org.alexsandrov.spring.database.pool.ConnectionPool;

import org.alexsandrov.spring.database.repository.CompanyRepository;
import org.alexsandrov.spring.database.repository.CrudRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
//            var connectionPool = context.getBean("connectionPool", ConnectionPool.class);
//            System.out.println(connectionPool);

            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository);
        }
    }
}