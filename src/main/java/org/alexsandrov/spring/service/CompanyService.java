package org.alexsandrov.spring.service;

import lombok.RequiredArgsConstructor;
import org.alexsandrov.spring.database.entity.Company;
import org.alexsandrov.spring.database.repository.CrudRepository;
import org.alexsandrov.spring.dto.CompanyReadDto;
import org.alexsandrov.spring.listner.entity.AccessType;
import org.alexsandrov.spring.listner.entity.EntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CompanyService {
    private final CrudRepository<Integer, Company> companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;


    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    applicationEventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.id());
                });
    }

}
