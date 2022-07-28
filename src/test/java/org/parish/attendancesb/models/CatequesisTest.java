package org.parish.attendancesb.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.parish.attendancesb.AttendanceSbApplication;
import org.parish.attendancesb.models.datetime.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

class CatequesisTest {

    private Catequesis catequesis;

    @BeforeEach
    void setUp() {
        catequesis = new Catequesis();
        catequesis.setTimeStart(new Time("03:30 PM"));
        catequesis.setTimeEnd(new Time("06:00 PM"));
        catequesis.setTolerance(10);
    }

    @Test
    void getTimeStartWithTolerance() {
        assertEquals(new Time("03:40 PM"), catequesis.getTimeStartWithTolerance());
    }

    @Test
    void getTimeMiddle() {
        assertEquals(new Time("04:45 PM"), catequesis.getTimeMiddle());
    }

}