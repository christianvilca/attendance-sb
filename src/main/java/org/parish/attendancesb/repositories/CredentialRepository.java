package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.access.Credential;
import org.parish.attendancesb.models.access.Role;
import org.parish.attendancesb.models.access.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {
    boolean existsByUser(User user);

    //Credential findByUser(User user);

    boolean existsByUserAndRoleAndCatequesis(User user, Role role, Catequesis catequesis);

    Credential findByUserAndRoleAndCatequesis(User user, Role role, Catequesis catequesis);

    @Override
    List<Credential> findAllById(Iterable<Integer> integers);

    List<Credential> findByUser(User user);


}
