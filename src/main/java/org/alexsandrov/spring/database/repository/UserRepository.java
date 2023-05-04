package org.alexsandrov.spring.database.repository;


import org.alexsandrov.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u " +
           "FROM User u " +
           "WHERE u.firstname like %:firstname% AND u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(nativeQuery = true,
            value = "SELECT * FROM users u " +
                    "WHERE u.username = :username")
    List<User> findAllByUsername(String username);
}
