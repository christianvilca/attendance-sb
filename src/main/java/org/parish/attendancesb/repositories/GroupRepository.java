package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
