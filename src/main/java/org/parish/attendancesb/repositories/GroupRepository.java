package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    public List<Group> findAllByNameContaining(String name);

    public List<Group> findAllByCatequesis(Catequesis catequesis);

    @Query("SELECT  case when count(r)> 0 then true else false end " +
            " FROM group_catequesis r" +
            " WHERE r.name = :#{#group_catequesis.name} " +
            " AND r.catequesis = :#{#group_catequesis.catequesis} ")
    boolean contains(@Param("group_catequesis") Group group);

}
