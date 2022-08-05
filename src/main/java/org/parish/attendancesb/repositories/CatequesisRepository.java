package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Catequesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatequesisRepository extends JpaRepository<Catequesis, Integer> {

    public List<Catequesis> findByNameContaining(String name);

}
