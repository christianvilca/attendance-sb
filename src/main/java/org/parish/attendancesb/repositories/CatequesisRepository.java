package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Catequesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatequesisRepository extends JpaRepository<Catequesis, Integer> {

}
