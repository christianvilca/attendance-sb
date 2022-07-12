package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.AttendanceDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceDateRepository extends JpaRepository<AttendanceDate, Integer> {
}
