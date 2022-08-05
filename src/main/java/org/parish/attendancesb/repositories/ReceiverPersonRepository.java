package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.ReceiverPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiverPersonRepository extends JpaRepository<ReceiverPerson, Integer> {

    public Optional<ReceiverPerson> findByCode(String code);

    @Query("SELECT r" +
            " FROM ReceiverPerson r" +
            " WHERE r.firstName like %?1% " +
            " OR r.lastName like %?1% ")
    public List<ReceiverPerson> findByNameContaining(String name);

    public List<ReceiverPerson> findByGroup(Group group);

}
