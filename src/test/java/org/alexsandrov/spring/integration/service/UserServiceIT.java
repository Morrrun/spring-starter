package org.alexsandrov.spring.integration.service;


import lombok.RequiredArgsConstructor;
import org.alexsandrov.spring.database.entity.Role;
import org.alexsandrov.spring.dto.UserCreateEditDto;
import org.alexsandrov.spring.dto.UserReadDto;
import org.alexsandrov.spring.integration.IntegrationTestBase;
import org.alexsandrov.spring.service.UserService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {


    public static final Long USER_ID_IVAN = 1L;
    public static final Integer COMPANY_ID = 1;
    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_ID_IVAN);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "test",
                "test",
                Role.ADMIN,
                COMPANY_ID
        );
        UserReadDto actualResult = userService.create(userDto);

        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(userDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userDto.getLastname(), actualResult.getLastname());
        assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
        assertSame(userDto.getRole(), actualResult.getRole());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "test",
                "test",
                Role.ADMIN,
                COMPANY_ID
        );
        Optional<UserReadDto> actualResult = userService.update(USER_ID_IVAN, userDto);

        assertTrue(actualResult.isPresent());

        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
            assertSame(userDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        assertFalse(userService.delete(-124L));
        assertTrue(userService.delete(USER_ID_IVAN));
    }

}
