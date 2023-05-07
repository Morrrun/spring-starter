package org.alexsandrov.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.alexsandrov.spring.config.DatabaseProperties;
import org.alexsandrov.spring.dto.CompanyReadDto;
import org.alexsandrov.spring.integration.annotation.IntegrationTest;
import org.alexsandrov.spring.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



@IntegrationTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class CompanyServiceIT {

    public static final Integer COMPANY_ID = 1;
    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {
        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID, "Google");
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }

}