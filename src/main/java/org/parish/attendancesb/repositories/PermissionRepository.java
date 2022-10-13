package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.access.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    Permission findByName(String name);

    List<Permission> findAllByNameContaining(String name);
}