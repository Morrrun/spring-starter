package org.alexsandrov.spring.database.repository;

import org.alexsandrov.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("SELECT c FROM Company c " +
           "JOIN fetch c.locales cl " +
           "WHERE lower(c.name) = lower(:name2)")
    Optional<Company> findByName(@Param("name2") String name);

    List<Company> findAllByNameContainingIgnoreCase(String fragment);

}
