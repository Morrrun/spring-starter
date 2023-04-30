package org.alexsandrov.spring;

import org.alexsandrov.spring.database.pool.ConnectionPool;

import org.alexsandrov.spring.database.repository.CompanyRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        // TODO: 30.04.2023 Получение бина по id с указанием класса
        var connectionPool = context.getBean("pool1", ConnectionPool.class);
        System.out.println(connectionPool);
        var companyRepository = context.getBean("companyRepository", CompanyRepository.class);
    }
}