package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.access.User;
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
            " WHERE r.name = :#{#catequesis.name} " +
            " AND r.day = :#{#catequesis.day} " +
            " AND r.timeStart = :#{#catequesis.timeStart} " +
            " AND r.timeEnd = :#{#catequesis.timeEnd} " +
            " AND r.tolerance = :#{#catequesis.tolerance} " +
            " AND r.receiverPersonType = :#{#catequesis.receiverPersonType} " )
    boolean contains(@Param("catequesis") Catequesis catequesis);

    List<Catequesis> findByUsers(User user);

    long countByUsers(User users);

    List<Catequesis> findByCatequistas_User(User user);

    long countByCatequistas_User(User user);

    boolean existsByUsers(User users);



}
