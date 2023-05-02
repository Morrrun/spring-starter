package org.alexsandrov.spring.database.repository;


import lombok.RequiredArgsConstructor;
import org.alexsandrov.spring.bpp.Auditing;
import org.alexsandrov.spring.bpp.Transaction;
import org.alexsandrov.spring.database.entity.Company;
import org.alexsandrov.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Repository
@Transaction
@Auditing
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company>{
    private final ConnectionPool pool1;
    private final List<ConnectionPool> pools;
    @Value("25")
    private final Integer poolSize;


    @PostConstruct
    private void init() {
        System.out.println("init company repository");
    }
    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method...");
    }

}
