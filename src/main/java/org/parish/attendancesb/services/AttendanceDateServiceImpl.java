package org.parish.attendancesb.services;

import org.parish.attendancesb.models.AttendanceDate;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.repositories.AttendanceDateRepository;
import org.parish.attendancesb.services.interfaces.AttendanceDateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceDateServiceImpl implements AttendanceDateService {

    private AttendanceDateRepository repository;

    public AttendanceDateServiceImpl(AttendanceDateRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AttendanceDate> findAllByCatequesis(Catequesis catequesis) {
        return repository.findAllByCatequesis(catequesis);
    }

    private Boolean hasDate(AttendanceDate attendanceDate) {
        return repository.findByDate(attendanceDate.getDate()) != null;
    }

    @Override
    public void save(AttendanceDate attendanceDate) {
        if (!hasDate(attendanceDate))
            repository.save(attendanceDate);
    }

}
