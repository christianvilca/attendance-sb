package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.Catequesis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatequesisRepository extends JpaRepository<Catequesis, Integer> {

}
