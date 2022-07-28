package org.parish.attendancesb.models.datetime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeTest {

    private DateTime dateTime;

    @BeforeEach
    void setUp() {
        dateTime = new DateTime("2022-01-01T03:30:00");
    }

    @Test
    void isBefore() {
        assertEquals(false, dateTime.isBefore("2022-01-01T03:29:59"));
        assertEquals(false, dateTime.isBefore("2022-01-01T03:30:00"));
        assertEquals(true, dateTime.isBefore("2022-01-01T03:30:01"));
    }

    @Test
    void isAfter() {
        assertEquals(true, dateTime.isAfter("2022-01-01T03:29:59"));
        assertEquals(false, dateTime.isAfter("2022-01-01T03:30:00"));
        assertEquals(false, dateTime.isAfter("2022-01-01T03:30:01"));
    }

    @Test
    void isBeforeWithDateTime() {
        assertEquals(false, dateTime.isBefore(new DateTime("2022-01-01T03:29:59")));
        assertEquals(false, dateTime.isBefore(new DateTime("2022-01-01T03:30:00")));
        assertEquals(true, dateTime.isBefore(new DateTime("2022-01-01T03:30:01")));
    }

    @Test
    void isAfterWithDateTime() {
        assertEquals(true, dateTime.isAfter(new DateTime("2022-01-01T03:29:59")));
        assertEquals(false, dateTime.isAfter(new DateTime("2022-01-01T03:30:00")));
        assertEquals(false, dateTime.isAfter(new DateTime("2022-01-01T03:30:01")));
    }

    @Test
    void getDate() {
        assertEquals(new Date("2022-01-01"), dateTime.getDate());
    }

    @Test
    void getTime() {
        assertEquals(new Time("03:30 AM"), dateTime.getTime());
    }
}