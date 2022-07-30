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
    private AttendanceRepository attendanceRepository;
    private CatequesisService catequesisService;
    private ReceiverPersonService receiverPersonService;
    private AttendanceDateService attendanceDateService;
    private AttendanceService attendanceService;

    public AttendanceServiceImpl() {
    }

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, ReceiverPersonService receiverPersonService) {
        this.attendanceRepository = attendanceRepository;
        this.receiverPersonService = receiverPersonService;
    }

    public Optional<Attendance> register(String code, String dateTime) {
        Optional<ReceiverPerson> optReceiverPerson = receiverPersonService.getByCode(code);
        ReceiverPerson receiverPerson = optReceiverPerson.get();
        Catequesis catequesis = this.catequesisService.get();

        Attendance attendance = new Attendance();
        attendance.setReceiverPerson(receiverPerson);
        attendance.setCatequesis(catequesis);
        attendance.setDateTime(new DateTime(dateTime));

        attendanceRepository.save(attendance);
        //this.insert(attendance);

        return Optional.of(attendance);
    }

    public Resume resume(Integer id) {
        ReceiverPerson receiverPerson = this.receiverPersonService.getById(id);
        Catequesis catequesis = this.catequesisService.get();
        List<AttendanceDate> attendanceDateList = this.attendanceDateService.getAllByIdCatequesis(catequesis.getId());
        List<Attendance> attendanceList = this.attendanceRepository.findByCatequesisAndReceiverPerson(receiverPerson.getId(), catequesis.getId());

        Resume resume = new Resume(attendanceDateList, attendanceList);

        for (Attendance attendance1 : attendanceList) {
            System.out.println("Attendance: " + attendance1);
        }
        for (AttendanceDate attendance2 : attendanceDateList) {
            System.out.println("AttendanceDate: " + attendance2);
        }

        return resume;
    }

}
