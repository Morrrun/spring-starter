package org.alexsandrov.spring.database.repository;

import jakarta.annotation.PostConstruct;
import org.alexsandrov.spring.bpp.Auditing;
import org.alexsandrov.spring.bpp.InjectBean;
import org.alexsandrov.spring.bpp.Transaction;
import org.alexsandrov.spring.database.entity.Company;
import org.alexsandrov.spring.database.pool.ConnectionPool;

import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company>{
    @InjectBean
    private ConnectionPool connectionPool;

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
