package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.access.Role;
import org.parish.attendancesb.models.access.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    List<User> findByUsernameContaining(String username);

    @Query("SELECT  case when count(r)> 0 then true else false end " +
            " FROM User r" +
            " WHERE  r.username = :#{#user.username} " +
            " AND r.password = :#{#user.password} ")
    boolean contains(@Param("user") User user);

    boolean existsByRoles(Role roles);

    boolean existsByUsername(String username);


}