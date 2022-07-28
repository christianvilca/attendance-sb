package org.parish.attendancesb.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.parish.attendancesb.models.datetime.Date;
import org.parish.attendancesb.models.datetime.DateTime;
import org.parish.attendancesb.models.datetime.Time;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceDateTest {

    private AttendanceDate attendanceDate;

    @BeforeEach
    void setUp() {
        attendanceDate = new AttendanceDate();
        attendanceDate.setDate(new Date("2022-01-01"));
    }

    @Test
    void getMonth() {
        assertEquals("Enero", attendanceDate.getMonth());
    }

    @Test
    void getDay() {
        assertEquals(1, attendanceDate.getDay());
    }
}