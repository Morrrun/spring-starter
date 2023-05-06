package org.alexsandrov.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.alexsandrov.spring.database.entity.Company;
import org.alexsandrov.spring.database.repository.CompanyRepository;
import org.alexsandrov.spring.integration.IntegrationTestBase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
//@Transactional
//@Commit
class CompanyRepositoryIT extends IntegrationTestBase {
    public static final Integer APPLE_ID = 6;
    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries() {
        companyRepository.findByName("google");

        companyRepository.findAllByNameContainingIgnoreCase("a");
    }


    @Test
    @Disabled
    void delete() {
        Optional<Company> maybeCompany = companyRepository.findById(APPLE_ID);

        assertTrue(maybeCompany.isPresent());

        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();

        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(tx -> {
            Company company = entityManager.find(Company.class, 1);

            assertNotNull(company);
            assertThat(company.getLocales()).hasSize(2);
        });

    }

    @Test
    void save() {
        Company company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Apple Описание",
                        "en", "Apple descriptor"
                ))
                .build();

        entityManager.persist(company);
        assertNotNull(company.getId());
    }

}