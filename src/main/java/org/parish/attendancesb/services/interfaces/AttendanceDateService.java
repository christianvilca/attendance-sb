package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.AttendanceDate;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.datetime.Date;

import java.util.List;

public interface AttendanceDateService {

    public void save(AttendanceDate attendanceDate);

    public List<AttendanceDate> findAllByCatequesis(Catequesis catequesis);

}
