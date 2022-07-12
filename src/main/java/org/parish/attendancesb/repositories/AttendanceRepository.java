package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
}
