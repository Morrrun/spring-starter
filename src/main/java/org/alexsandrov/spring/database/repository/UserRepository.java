package org.alexsandrov.spring.database.repository;


import org.alexsandrov.spring.database.entity.Role;
import org.alexsandrov.spring.database.entity.User;
import org.alexsandrov.spring.database.repository.custom.FilterUserRepository;
import org.alexsandrov.spring.dto.PersonalInfo2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, FilterUserRepository {
    @Query("SELECT u " +
           "FROM User u " +
           "WHERE u.firstname like %:firstname% AND u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(nativeQuery = true,
            value = "SELECT * FROM users u " +
                    "WHERE u.username = :username")
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u " +
           "SET u.role = :role " +
           "WHERE u.id in (:ids)")
    int updateRole(Role role, Long... ids);


    Optional<User> findTopByOrderByIdDesc();

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    @Lock(LockModeType.PESSIMISTIC_READ)
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);
//    @EntityGraph("User.company")
//                                   граф           подграф
//                                    ||               ||
//                                    \/               \/
    @EntityGraph(attributePaths = {"company", "company.locales"})
    @Query(value = "SELECT u FROM User u",
            countQuery = "SELECT count(distinct u.firstname) FROM User u")
    Page<User> findAllBy(Pageable pageable);


//    List<PersonalInfo> findAllByCompanyId(Integer companyId);
//    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);

    @Query(
            nativeQuery = true,
            value = "SELECT firstname, lastname, birth_date birthDate " +
                    "FROM users " +
                    "WHERE company_id = :companyId"
    )
    List<PersonalInfo2> findAllByCompanyId(Integer companyId);
}
