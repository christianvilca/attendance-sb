package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.AttendanceDate;
import org.parish.attendancesb.models.Catequesis;

import java.util.List;

public interface AttendanceDateService extends Service<Integer, AttendanceDate> {

    public List<AttendanceDate> findAllByCatequesis(Catequesis catequesis);

}
