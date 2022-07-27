package org.parish.attendancesb.models.datetime;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    private Time time;

    @BeforeEach
    void setUp() {
        time = new Time("03:30 a.m.");
    }

    @Test
    void to_String() {
        assertEquals("03:30", time.toString());
    }

    @Test
    void getLocalTime() {
        assertEquals("03:30", time.getLocalTime().toString());
    }

    @Test
    void parse() {
        assertEquals("03:30", new Time("3:30 a.m.").getLocalTime().toString());
        assertEquals("12:30", new Time("12:30 p.m.").getLocalTime().toString());
    }

    @Test
    void plusMinutes() {
        assertEquals(new Time("03:40 a.m."), time.plusMinutes(10));
        assertEquals(new Time("12:00 a.m."), new Time("11:59 p.m.").plusMinutes(1));
        assertEquals(new Time("12:00 p.m."), new Time("11:59 a.m.").plusMinutes(1));
        assertEquals(new Time("12:00 PM"), new Time("11:59 a.m.").plusMinutes(1));
        assertEquals(new Time("01:00 PM"), new Time("11:00 a.m.").plusMinutes(120));
    }

    @Test
    void plusSeconds() {
        assertEquals(new Time("12:01 p.m."), new Time("11:59 a.m.").plusSeconds(120));
        assertEquals(new Time("12:00 p.m."), new Time("11:59 a.m.").plusSeconds(60));
    }

    @Test
    void getHour() {
        assertEquals(3, time.getHour());
    }

    @Test
    void getMinute() {
        assertEquals(30, time.getMinute());
    }

    @Test
    void duration() {
        assertEquals(60, time.duration(new Time("03:31 a.m.")));
    }

    @Test
    void getHourAMPM() {
        assertEquals("03:30 AM", time.getHourAMPM());
    }

}