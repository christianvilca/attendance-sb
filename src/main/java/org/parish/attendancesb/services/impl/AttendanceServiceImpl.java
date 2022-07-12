package org.parish.attendancesb.services.impl;

import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.models.datetime.DateTime;
import org.parish.attendancesb.repositories.AttendanceRepository;
import org.parish.attendancesb.services.AttendanceService;
import org.parish.attendancesb.services.CatequesisService;
import org.parish.attendancesb.services.ReceiverPersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AttendanceServiceImpl implements AttendanceService {
    private AttendanceRepository attendanceRepository;
    private CatequesisService catequesisService;
    private ReceiverPersonService receiverPersonService;

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

    
}
