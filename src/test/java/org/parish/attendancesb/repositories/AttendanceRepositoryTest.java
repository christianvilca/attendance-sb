package org.parish.attendancesb.repositories;

import org.junit.jupiter.api.Test;
import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.models.datetime.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AttendanceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    AttendanceRepository repository;

    @Test
    public void should_find_no_attendance_if_repository_is_empty() {
        Iterable attendances = repository.findAll();

        assertThat(attendances).isEmpty();
    }

    @Test
    public void should_store_a_attendance() {
        Attendance attendance = repository.save(new Attendance(new DateTime("2022-01-01T15:30"), new Catequesis(1), new ReceiverPerson(1)));

        assertThat(attendance).hasFieldOrPropertyWithValue("catequesis.id", 1);
        assertThat(attendance).hasFieldOrPropertyWithValue("receiverPerson.id", 1);
        assertThat(attendance).hasFieldOrPropertyWithValue("dateTime", new DateTime("2022-01-01T15:30"));
    }

    @Test
    public void should_find_all_attendance() {
        Attendance at1 = new Attendance(new DateTime("2022-01-01T15:30"), new Catequesis(1), new ReceiverPerson(1));
        entityManager.persist(at1);
        Attendance at2 = new Attendance(new DateTime("2022-01-01T16:30"), new Catequesis(1), new ReceiverPerson(1));
        entityManager.persist(at2);
        Attendance at3 = new Attendance(new DateTime("2022-01-01T17:30"), new Catequesis(1), new ReceiverPerson(1));
        entityManager.persist(at3);

        Iterable attendanceDates = repository.findAll();
        assertThat(attendanceDates).hasSize(3).contains(at1, at2, at3);
    }

    @Test
    public void should_find_attendance_by_catequesis_and_ReceiverPerson() {
        Attendance at1 = new Attendance(new DateTime("2022-01-01T15:30"), new Catequesis(1), new ReceiverPerson(1));
        entityManager.persist(at1);
        Attendance at2 = new Attendance(new DateTime("2022-01-01T16:30"), new Catequesis(1), new ReceiverPerson(1));
        entityManager.persist(at2);
        Attendance at3 = new Attendance(new DateTime("2022-01-01T17:30"), new Catequesis(2), new ReceiverPerson(2));
        entityManager.persist(at3);
        Attendance at4 = new Attendance(new DateTime("2022-01-01T18:30"), new Catequesis(2), new ReceiverPerson(2));
        entityManager.persist(at4);

        Iterable receiverPeople1 = repository.findByCatequesisAndReceiverPerson(new Catequesis(1), new ReceiverPerson(1));
        assertThat(receiverPeople1).hasSize(2).contains(at1, at2);

        Iterable receiverPeople2 = repository.findByCatequesisAndReceiverPerson(new Catequesis(2), new ReceiverPerson(2));
        assertThat(receiverPeople2).hasSize(2).contains(at3, at4);
    }

}