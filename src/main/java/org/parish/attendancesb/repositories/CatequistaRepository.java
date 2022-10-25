package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatequistaRepository extends JpaRepository<Catequista, Integer> {
    @Query("SELECT r" +
            " FROM Catequista r" +
            " WHERE r.firstName like %?1% " +
            " OR r.lastName like %?1% ")
    List<Catequista> findByNameContaining(String name);

    //List<Catequista> findByGroup(Group group);

    @Query("SELECT  case when count(r)> 0 then true else false end " +
            " FROM Catequista r" +
            " WHERE r.firstName = :#{#person.firstName} " +
            " AND r.lastName = :#{#person.lastName} " +
            " AND r.groups = :#{#person.groups} ")
    boolean contains(@Param("person") Catequista person);

    @Override
    boolean existsById(Integer integer);
}