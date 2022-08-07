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

    @Override
    public AttendanceDate getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public AttendanceDate save(AttendanceDate attendanceDate) {
        return repository.save(attendanceDate);
    }

    @Override
    public void delete(AttendanceDate attendanceDate) {
        repository.delete(attendanceDate);
    }

    @Override
    public AttendanceDate update(AttendanceDate attendanceDate) {
        return repository.findById(attendanceDate.getId())
                .map(
                        ad -> {
                            ad.setCatequesis(attendanceDate.getCatequesis());
                            ad.setDate(attendanceDate.getDate());
                            return repository.save(ad);
                        }
                ).orElse(null);
    }

    @Override
    public List<AttendanceDate> findAll() {
        return repository.findAll();
    }

}
