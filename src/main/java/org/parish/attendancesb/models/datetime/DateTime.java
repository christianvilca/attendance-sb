package org.parish.attendancesb.models.datetime;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@ToString
public class DateTime {
    private LocalDateTime localDateTime;
    private Time time;
    private Date date;

    public DateTime() {
        this.localDateTime = LocalDateTime.now();
    }

    public DateTime(String dateTime) {
        this.localDateTime = LocalDateTime.parse(dateTime);
    }

    public DateTime(LocalDate date, LocalTime time) {
        localDateTime = LocalDateTime.of(date, time);
    }

    public DateTime(Date date, Time time) {
        localDateTime = LocalDateTime.of(date.getLocalDate(), time.getLocalTime());
    }

    public DateTime(int year, int month, int day) {
        date.set(year, month, day);
    }

    public DateTime(int year, int month, int day, int hour, int minute) {
        date.set(year, month, day);
        time.set(hour, minute);
    }

    public boolean isAfter(String dateTime) {
        return this.localDateTime.isAfter(LocalDateTime.parse(dateTime));
    }

    public static void main(String[] args) {

        System.out.println(new DateTime());
    }
}
