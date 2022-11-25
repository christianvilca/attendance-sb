package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Catequesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatequesisRepository extends JpaRepository<Catequesis, Integer> {

    List<Catequesis> findByNameContaining(String name);

    @Query("SELECT  case when count(r)> 0 then true else false end " +
            " FROM Catequesis r" +
            " WHERE r.name = :#{#catequesis.name} ")
    boolean contains(@Param("catequesis") Catequesis catequesis);

}
