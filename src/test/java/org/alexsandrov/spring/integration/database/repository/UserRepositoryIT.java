package org.alexsandrov.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.alexsandrov.spring.database.entity.Role;
import org.alexsandrov.spring.database.entity.User;
import org.alexsandrov.spring.database.repository.UserRepository;
import org.alexsandrov.spring.dto.PersonalInfo2;
import org.alexsandrov.spring.integration.annotation.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@IntegrationTest
@RequiredArgsConstructor
class UserRepositoryIT {
    private final UserRepository userRepository;

    @Test
    void checkProjections() {
        List<PersonalInfo2> users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
        System.out.println();
    }
    @Test
    void checkPageable() {
        var pageable = PageRequest.of(0, 2, Sort.by("id"));

        Slice<User> usersSlice = userRepository.findAllBy(pageable);
        assertThat(usersSlice).hasSize(2);

        usersSlice.forEach(user -> System.out.println(user.getCompany().getName()));

        while (usersSlice.hasNext()) {
            usersSlice = userRepository.findAllBy(usersSlice.nextPageable());
            usersSlice.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkSort() {
        var sortBy = Sort.sort(User.class);
        var sortUser = sortBy.by(User::getFirstname)
                .and(sortBy.by(User::getLastname));

        List<User> allUsers = userRepository
                .findTop3ByBirthDateBefore(LocalDate.now(), sortUser);
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFirstTop() {
        var sortBy = Sort.by("firstname").and(Sort.by("lastname"));

        List<User> allUsers = userRepository
                .findTop3ByBirthDateBefore(LocalDate.now(), sortBy);
        assertThat(allUsers).hasSize(3);

        Optional<User> topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkUpdate() {
        User ivan = userRepository.getById(1L);
        assertSame(Role.ADMIN, ivan.getRole());

        int resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        User theSameIvan = userRepository.getById(1L);
        assertSame(Role.USER, theSameIvan.getRole());
    }

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);

    }
}