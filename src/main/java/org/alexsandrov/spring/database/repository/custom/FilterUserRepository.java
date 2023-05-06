package org.alexsandrov.spring.database.repository.custom;

import org.alexsandrov.spring.database.entity.Role;
import org.alexsandrov.spring.database.entity.User;
import org.alexsandrov.spring.dto.PersonalInfo;
import org.alexsandrov.spring.dto.PersonalInfo2;
import org.alexsandrov.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);
    void updateCompanyAndRoleNamed(List<User> users);
}
