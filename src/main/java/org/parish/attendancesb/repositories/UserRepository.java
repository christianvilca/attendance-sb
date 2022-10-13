package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.access.Role;
import org.parish.attendancesb.models.access.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT r" +
            " FROM User r" +
            " WHERE r.firstName like %?1% " +
            " OR r.lastName like %?1% ")
    List<User> findByNameContaining(String name);
    User findByUsername(String username);

    @Query("SELECT  case when count(r)> 0 then true else false end " +
            " FROM User r" +
            " WHERE r.firstName = :#{#user.firstName} " +
            " AND r.lastName = :#{#user.lastName} " +
            " AND r.username = :#{#user.username} " +
            //" AND r.roles = :#{#user.roles} " +
            " AND r.group = :#{#user.group} ")
    boolean contains(@Param("user") User person);

    boolean existsByRoles(Role roles);

    boolean existsByUsername(String username);



}