package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.AttendanceDate;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.models.datetime.DateTime;
import org.parish.attendancesb.repositories.AttendanceRepository;
import org.parish.attendancesb.services.interfaces.AttendanceDateService;
import org.parish.attendancesb.services.interfaces.AttendanceService;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.parish.attendancesb.services.attendance.Resume;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private AttendanceRepository repository;
    private CatequesisService catequesisService;
    private ReceiverPersonService receiverPersonService;
    private AttendanceDateService attendanceDateService;
    private AttendanceService attendanceService;

    public AttendanceServiceImpl() {
    }

    public AttendanceServiceImpl(
            AttendanceRepository repository,
            CatequesisService catequesisService,
            ReceiverPersonService receiverPersonService,
            AttendanceDateService attendanceDateService,
            AttendanceService attendanceService
    ) {
        this.repository = repository;
        this.catequesisService = catequesisService;
        this.receiverPersonService = receiverPersonService;
        this.attendanceDateService = attendanceDateService;
        this.attendanceService = attendanceService;
    }

    public Optional<Attendance> register(String code, String dateTime) {
        Optional<ReceiverPerson> optReceiverPerson = receiverPersonService.findByCode(code);
        ReceiverPerson receiverPerson = optReceiverPerson.get();
        Catequesis catequesis = this.catequesisService.get();

        Attendance attendance = new Attendance();
        attendance.setReceiverPerson(receiverPerson);
        attendance.setCatequesis(catequesis);
        attendance.setDateTime(new DateTime(dateTime));

        this.save(attendance);

        return Optional.of(attendance);
    }

    @Override
    public Resume resume(ReceiverPerson receiverPerson) {
        Catequesis catequesis = this.catequesisService.get();
        List<AttendanceDate> attendanceDateList = this.attendanceDateService.findAllByCatequesis(catequesis);
        List<Attendance> attendanceList = this.repository.findByCatequesisAndReceiverPerson(catequesis, receiverPerson);

        Resume resume = new Resume(attendanceDateList, attendanceList);
        resume.generate();

        return resume;
    }

    @Override
    public Attendance save(Attendance attendance) {
        attendance.setDateTime(new DateTime());

        this.attendanceDateService.save(this.getAttendanceDate(attendance));
        return repository.save(attendance);
    }

    private AttendanceDate getAttendanceDate(Attendance attendance) {
        AttendanceDate attendanceDate = new AttendanceDate();
        attendanceDate.setCatequesis(this.catequesisService.get());
        attendanceDate.setDate(attendance.getDateTime().getDate());

        return attendanceDate;
    }

}
