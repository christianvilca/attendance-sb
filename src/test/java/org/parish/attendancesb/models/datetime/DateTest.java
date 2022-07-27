package org.parish.attendancesb.models.datetime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    private Date date;

    @BeforeEach
    void setUp() {
        date = new Date("2022-01-01");
    }

    @Test
    void parse() {
        assertEquals("2022-01-01", new Date("2022-01-01").getLocalDate().toString());
    }

    @Test
    void getYear() {
        assertEquals(2022, date.getYear());
    }

    @Test
    void getMonth() {
        assertEquals("Enero", date.getMonth());
    }

    @Test
    void getMonthValue() {
        assertEquals(1, date.getMonthValue());
    }

    @Test
    void getDay() {
        assertEquals(1, date.getDay());
    }

    @Test
    void getDate() {
        assertEquals("2022-01-01", date.getDate());
    }
}