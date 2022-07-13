package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.ReceiverPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceiverPersonRepository extends JpaRepository<ReceiverPerson, Integer> {

    public Optional<ReceiverPerson> findByCode(String code);
}
