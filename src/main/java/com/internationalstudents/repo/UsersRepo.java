package com.internationalstudents.repo;

import com.internationalstudents.model.Users;
import com.internationalstudents.model.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    List<Users> findAllByOrderByRole();

    List<Users> findAllByRoleAndSecondary_JobContainingAndTertiary_MaritalAndTertiary_CitizenshipAndTertiary_Conscripted(Role role, String job,    Marital marital, Citizenship citizenship, YesNo conscripted);

    List<Users> findAllByRole(Role role);

    List<Users> findAllByRoleAndTertiary_Marital(Role role, Marital marital);

    List<Users> findAllByRoleAndTertiary_Citizenship(Role role, Citizenship citizenship);

    List<Users> findAllByRoleAndTertiary_Conscripted(Role role, YesNo yesNo);
}
