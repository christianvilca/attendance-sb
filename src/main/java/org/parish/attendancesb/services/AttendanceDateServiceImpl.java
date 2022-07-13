package org.parish.attendancesb.services;

import org.parish.attendancesb.models.AttendanceDate;
import org.parish.attendancesb.services.interfaces.AttendanceDateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceDateServiceImpl implements AttendanceDateService {
    @Override
    public List<AttendanceDate> getAllByIdCatequesis(Integer idCatequesis) {
        return null;
    }
}
