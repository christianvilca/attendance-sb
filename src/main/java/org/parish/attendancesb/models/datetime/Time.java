package org.parish.attendancesb.models.datetime;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@ToString
@RequiredArgsConstructor
public class Time {
    private LocalTime localTime;
    private static final String FORMAT_TIME_AMPM = "hh:mm a";

    public Time(LocalTime localTime) {
        this.set(localTime);
    }

    public Time(String time) {
        this.parse(time);
    }

    public Time(int hour, int minute) {
        this.set(hour, minute);
    }

    public Time(int hour, int minute, int second) {
        this.set(hour, minute, second);
    }

    public void set(LocalTime localTime) {
        this.localTime = localTime;
    }

    public void set(int hour, int minute) {
        localTime = LocalTime.of(hour, minute);
    }

    public void set(int hour, int minute, int second) {
        localTime = LocalTime.of(hour, minute, second);
    }

    public LocalTime get() {
        return localTime;
    }

    public void parse(String time) {
        localTime = LocalTime.parse(this.format(time), DateTimeFormatter.ofPattern(FORMAT_TIME_AMPM));
    }

    private String format(String time) {
        time = time.replace("AM", "a. m.");
        time = time.replace("PM", "p. m.");
        time = time.replace("a. m.", "a. m.");
        time = time.replace("p. m.", "p. m.");
        time = time.replace("a.m.", "a. m.");
        time = time.replace("p.m.", "p. m.");
        return time;
    }

    private String AMPM() {
        String time = this.localTime.format(DateTimeFormatter.ofPattern(FORMAT_TIME_AMPM));

        time = time.replace("a. m.", "AM");
        time = time.replace("p. m.", "PM");
        return time;
    }

    public static void main(String[] args) {
        System.out.println(new Time("03:30 a.m.").AMPM());
        System.out.println(new Time(3, 30));
        LocalTime local = LocalTime.of(3, 30);
        System.out.println(local.format(DateTimeFormatter.ofPattern("hh:mm a")));
    }
}
