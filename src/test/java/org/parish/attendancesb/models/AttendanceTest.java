package org.parish.attendancesb.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.parish.attendancesb.models.datetime.DateTime;
import org.parish.attendancesb.models.datetime.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceTest {

    private Attendance attendance;

    @BeforeEach
    void setUp() {
        Catequesis catequesis = new Catequesis();
        catequesis.setTimeStart(new Time("03:30 PM"));
        catequesis.setTimeEnd(new Time("06:00 PM"));
        catequesis.setTolerance(10);

        attendance = new Attendance();
        attendance.setCatequesis(catequesis);
    }

    @Test
    void isBeforeStart() {
        attendance.setDateTime(new DateTime("2022-01-01T15:39:59"));
        assertEquals(true, attendance.isBeforeStart());

        attendance.setDateTime(new DateTime("2022-01-01T15:40:00"));
        assertEquals(false, attendance.isBeforeStart());
    }

    @Test
    void isAfterStart() {
        attendance.setDateTime(new DateTime("2022-01-01T15:40:00"));
        assertEquals(false, attendance.isAfterStart());

        attendance.setDateTime(new DateTime("2022-01-01T15:40:01"));
        assertEquals(true, attendance.isAfterStart());
    }

    @Test
    void isBeforeMiddle() {
        attendance.setDateTime(new DateTime("2022-01-01T16:44:59"));
        assertEquals(true, attendance.isBeforeMiddle());

        attendance.setDateTime(new DateTime("2022-01-01T16:45:00"));
        assertEquals(false, attendance.isBeforeMiddle());
    }

    @Test
    void isAfterMiddle() {
        attendance.setDateTime(new DateTime("2022-01-01T16:45:00"));
        assertEquals(false, attendance.isAfterMiddle());

        attendance.setDateTime(new DateTime("2022-01-01T16:45:01"));
        assertEquals(true, attendance.isAfterMiddle());
    }

    @Test
    void toAMPM() {
        attendance.setDateTime(new DateTime("2022-01-01T15:30:00"));
        assertEquals("03:30 PM", attendance.toAMPM());
    }
}