package org.alexsandrov.spring;

import org.alexsandrov.spring.config.ApplicationConfiguration;
import org.alexsandrov.spring.database.pool.ConnectionPool;

import org.alexsandrov.spring.database.repository.CompanyRepository;
import org.alexsandrov.spring.database.repository.CrudRepository;
import org.alexsandrov.spring.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
//            var connectionPool = context.getBean("connectionPool", ConnectionPool.class);
//            System.out.println(connectionPool);

//            //Указываем актив-профили
//            context.getEnvironment().setActiveProfiles("web", "prod");
//            //Перезапускает ЖЦ бинов
//            context.refresh();

            var companyService = context.getBean("companyService", CompanyService.class);
            System.out.println(companyService.findById(1));
        }
    }
}