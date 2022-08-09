package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.AttendanceDate;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.datetime.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceDateRepository extends JpaRepository<AttendanceDate, Integer> {

    public List<AttendanceDate> findAllByCatequesis(Catequesis catequesis);

    public AttendanceDate findByDate(Date date);

}
