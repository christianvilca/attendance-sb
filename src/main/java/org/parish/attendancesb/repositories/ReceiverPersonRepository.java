package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.ReceiverPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiverPersonRepository extends JpaRepository<ReceiverPerson, Integer> {

    Optional<ReceiverPerson> findByCode(String code);

    @Query("SELECT r" +
            " FROM ReceiverPerson r" +
            " WHERE r.firstName like %?1% " +
            " OR r.lastName like %?1% ")
    List<ReceiverPerson> findByNameContaining(String name);

    List<ReceiverPerson> findByGroup(Group group);

    @Query("SELECT  case when count(r)> 0 then true else false end " +
            " FROM ReceiverPerson r" +
            " WHERE r.firstName = :#{#person.firstName} " +
            " AND r.lastName = :#{#person.lastName} " +
            " AND r.group = :#{#person.group} ")
    boolean contains(@Param("person") ReceiverPerson person);

}
