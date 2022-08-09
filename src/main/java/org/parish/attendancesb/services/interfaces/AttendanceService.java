package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.attendance.Resume;

public interface AttendanceService {

    public Attendance save(Attendance attendance);

    public Resume resume(ReceiverPerson receiverPerson);

}
