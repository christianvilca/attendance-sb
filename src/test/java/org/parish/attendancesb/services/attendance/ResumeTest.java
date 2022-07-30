package org.parish.attendancesb.services.attendance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.AttendanceDate;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.datetime.Date;
import org.parish.attendancesb.models.datetime.DateTime;
import org.parish.attendancesb.models.datetime.Time;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ResumeTest {

    private Resume resume;

    @BeforeEach
    void setUp() {
        Catequesis catequesis = new Catequesis();
        catequesis.setTimeStart(new Time("03:30 PM"));
        catequesis.setTimeEnd(new Time("06:00 PM"));
        catequesis.setTolerance(10);

        List<AttendanceDate> attendanceDateList = new ArrayList<>();
        attendanceDateList.add(new AttendanceDate(catequesis, new Date("2022-05-14"))); // WITHOUT | PRESENT
        attendanceDateList.add(new AttendanceDate(catequesis, new Date("2022-05-21"))); //      ABSENT
        attendanceDateList.add(new AttendanceDate(catequesis, new Date("2022-05-28"))); // PRESENT | WITHOUT
        attendanceDateList.add(new AttendanceDate(catequesis, new Date("2022-06-04"))); // LATE    | PRESENT
        attendanceDateList.add(new AttendanceDate(catequesis, new Date("2022-06-18")));

        List<Attendance> attendanceList = new ArrayList<>();

        // ------------------------------------- FIRST -------------------------------------
        // WITHOUT

        // ABSENT

        // PRESENT
        attendanceList.add(new Attendance(new DateTime("2022-05-28T15:25:00"), catequesis));
        attendanceList.add(new Attendance(new DateTime("2022-05-28T15:30:00"), catequesis));
        attendanceList.add(new Attendance(new DateTime("2022-05-28T15:39:00"), catequesis));

        // LATE
        attendanceList.add(new Attendance(new DateTime("2022-06-04T15:41:00"), catequesis));

        // ------------------------------------- LAST -------------------------------------
        // WITHOUT

        // ABSENT

        // PRESENT
        attendanceList.add(new Attendance(new DateTime("2022-05-14T16:46:00"), catequesis));
        attendanceList.add(new Attendance(new DateTime("2022-05-14T17:30:00"), catequesis));
        attendanceList.add(new Attendance(new DateTime("2022-05-14T18:00:00"), catequesis));

        attendanceList.add(new Attendance(new DateTime("2022-06-04T16:46:00"), catequesis));

//        resume.setAttendanceDateList(attendanceDateList);
//        resume.setAttendanceList(attendanceList);
//        resume.setResumeDetails(new ArrayList<>());
        resume = new Resume(attendanceDateList, attendanceList);
        resume.generate();
    }

    @Test
    void getPresents() {
        assertEquals(1, resume.getPresents());
    }

    @Test
    void getLates() {
        assertEquals(1, resume.getLates());
    }

    @Test
    void getAbsents() {
        assertEquals(2, resume.getAbsents());
    }

    @Test
    void getTotal() {
        assertEquals(5, resume.getTotal());
    }

    @Test
    void getResumeDetails() {
        assertEquals("Mayo", resume.getResumeDetails().get(0).getMonth());
        assertEquals(14, resume.getResumeDetails().get(0).getDay());
        assertEquals("NO REGISTRO", resume.getResumeDetails().get(0).getTimeFirst());
        assertEquals("06:00 PM", resume.getResumeDetails().get(0).getTimeLast());

        assertEquals("Mayo", resume.getResumeDetails().get(1).getMonth());
        assertEquals(21, resume.getResumeDetails().get(1).getDay());
        assertEquals("FALTA", resume.getResumeDetails().get(1).getTimeFirst());
        assertEquals("FALTA", resume.getResumeDetails().get(1).getTimeLast());

        assertEquals("Mayo", resume.getResumeDetails().get(2).getMonth());
        assertEquals(28, resume.getResumeDetails().get(2).getDay());
        assertEquals("03:25 PM", resume.getResumeDetails().get(2).getTimeFirst());
        assertEquals("NO REGISTRO", resume.getResumeDetails().get(2).getTimeLast());

        assertEquals("Junio", resume.getResumeDetails().get(3).getMonth());
        assertEquals(4, resume.getResumeDetails().get(3).getDay());
        assertEquals("03:41 PM", resume.getResumeDetails().get(3).getTimeFirst());
        assertEquals("04:46 PM", resume.getResumeDetails().get(3).getTimeLast());
    }
}