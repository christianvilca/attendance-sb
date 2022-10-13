package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.access.Permission;
import org.parish.attendancesb.models.access.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByPermissions(Permission permissions);

    Role findByName(String name);

}