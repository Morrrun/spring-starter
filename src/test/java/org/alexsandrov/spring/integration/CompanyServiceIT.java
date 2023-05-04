package org.alexsandrov.spring.integration;

import org.alexsandrov.spring.ApplicationRunner;
import org.alexsandrov.spring.dto.CompanyReadDto;
import org.alexsandrov.spring.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * classes = ApplicationRunner.class - является не обязательным т.к.
 * аннотация @SpringBootTest автоматически ищет класс в classpath
 * с аннотацией @SpringBootApplication
 */
@SpringBootTest(classes = ApplicationRunner.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(
//        classes = ApplicationRunner.class
//        Раньше указывалось для загрузки yml файлов конфигураций
//        initializers = ConfigDataApplicationContextInitializer.class
//)
//Только для файлов .properties
//@TestPropertySource("classpath:application.yml")
class CompanyServiceIT {

    public static final Integer COMPANY_ID = 1;
    @Autowired
    private CompanyService companyService;

    @Test
    void findById() {
        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }

}