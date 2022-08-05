package org.parish.attendancesb.repositories;

import org.junit.jupiter.api.Test;
import org.parish.attendancesb.models.AttendanceDate;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.datetime.Date;
import org.parish.attendancesb.models.datetime.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AttendanceDateRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    AttendanceDateRepository repository;

    @Test
    public void should_find_no_attendance_date_if_repository_is_empty() {
        Iterable attendanceDates = repository.findAll();

        assertThat(attendanceDates).isEmpty();
    }

    @Test
    public void should_store_a_attendance_date() {
        AttendanceDate attendanceDate = repository.save(new AttendanceDate(new Catequesis(1), new Date("2022-01-01")));

        assertThat(attendanceDate).hasFieldOrPropertyWithValue("catequesis.id", 1);
        assertThat(attendanceDate).hasFieldOrPropertyWithValue("date", new Date("2022-01-01"));
    }

    @Test
    public void should_find_all_attendance_date() {
        AttendanceDate ad1 = new AttendanceDate(new Catequesis(1), new Date("2022-01-01"));
        entityManager.persist(ad1);
        AttendanceDate ad2 = new AttendanceDate(new Catequesis(2), new Date("2022-01-01"));
        entityManager.persist(ad2);
        AttendanceDate ad3 = new AttendanceDate(new Catequesis(3), new Date("2022-01-01"));
        entityManager.persist(ad3);

        Iterable attendanceDates = repository.findAll();
        assertThat(attendanceDates).hasSize(3).contains(ad1, ad2, ad3);
    }

    @Test
    public void should_find_attendance_date_by_id() {
        AttendanceDate ad1 = new AttendanceDate(new Catequesis(1), new Date("2022-01-01"));
        entityManager.persist(ad1);
        AttendanceDate ad2 = new AttendanceDate(new Catequesis(2), new Date("2022-01-01"));
        entityManager.persist(ad2);

        AttendanceDate foundCatequesis = repository.findById(ad2.getId()).get();
        assertThat(foundCatequesis).isEqualTo(ad2);
    }

    @Test
    public void should_find_all_attendance_date_by_catequesis() {
        AttendanceDate ad1 = new AttendanceDate(new Catequesis(1), new Date("2022-01-01"));
        entityManager.persist(ad1);
        AttendanceDate ad2 = new AttendanceDate(new Catequesis(2), new Date("2022-01-01"));
        entityManager.persist(ad2);
        AttendanceDate ad3 = new AttendanceDate(new Catequesis(1), new Date("2022-01-02"));
        entityManager.persist(ad3);

        Iterable receiverPeople = repository.findAllByCatequesis(new Catequesis(1));
        assertThat(receiverPeople).hasSize(2).contains(ad1, ad3);
    }

}