package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.AttendanceDate;

import java.util.List;

public interface AttendanceDateService {
    public List<AttendanceDate> getAllByIdCatequesis(Integer idCatequesis);
}
