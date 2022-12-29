package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.Institution;
import org.parish.attendancesb.models.ReceiverPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Integer> {

    @Query("SELECT  case when count(r)> 0 then true else false end " +
            " FROM Institution r" +
            " WHERE r.institutionName = :#{#institution.institutionName} " +
            " AND r.name = :#{#institution.name} " +
            " AND r.logo = :#{#institution.logo} " )
    boolean contains(@Param("institution") Institution institution);

    @Override
    boolean existsById(Integer integer);
}
