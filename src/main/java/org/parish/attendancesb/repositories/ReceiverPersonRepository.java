package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.ReceiverPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceiverPersonRepository extends JpaRepository<ReceiverPerson, Integer> {

    public Optional<ReceiverPerson> findByCode(String code);
}
