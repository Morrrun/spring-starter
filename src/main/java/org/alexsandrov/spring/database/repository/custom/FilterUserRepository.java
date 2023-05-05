package org.alexsandrov.spring.database.repository.custom;

import org.alexsandrov.spring.database.entity.User;
import org.alexsandrov.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);
}
