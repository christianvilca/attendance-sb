package org.parish.attendancesb.models.datetime;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@ToString
@Component
@Embeddable
public class Time {
    private LocalTime localTime;

    private static final String FORMAT_TIME_AMPM = "hh:mm a";

    public Time() {
        this.localTime = LocalTime.now();
        this.localTime.format(DateTimeFormatter.ofPattern(FORMAT_TIME_AMPM));
    }

    public Time(String time) {
        this.parse(time);
    }

    private Time(LocalTime localTime) {
        this.localTime = localTime;
        this.localTime.format(DateTimeFormatter.ofPattern(FORMAT_TIME_AMPM));
    }

    public Time(int hour, int minute) {
        this.set(hour, minute);
    }

    public Time(int hour, int minute, int second) {
        this.set(hour, minute, second);
    }

    public void set(int hour, int minute) {
        localTime = LocalTime.of(hour, minute);
    }

    public void set(int hour, int minute, int second) {
        localTime = LocalTime.of(hour, minute, second);
    }

    public void parse(String time) {
        localTime = LocalTime.parse(this.format(time), DateTimeFormatter.ofPattern(FORMAT_TIME_AMPM));
    }

    public Time plusMinutes(int minutes) {
        LocalTime localTime = this.localTime;
        return new Time(localTime.plusMinutes(minutes));
    }

    public Time plusSeconds(int seconds) {
        LocalTime localTime = this.localTime;
        return new Time(localTime.plusSeconds(seconds));
    }

    private String format(String time) {
        assert time != null;

        if (time.substring(0, 2).contains(":"))
            time = "0" + time;

        time = time.replace("AM", "a. m.");
        time = time.replace("PM", "p. m.");
        time = time.replace("a. m.", "a. m.");
        time = time.replace("p. m.", "p. m.");
        time = time.replace("a.m.", "a. m.");
        time = time.replace("p.m.", "p. m.");

        return time;
    }

    public int getHour() {
        return this.localTime.getHour();
    }

    public int getMinute() {
        return this.localTime.getMinute();
    }

    public int duration(Time time) {
        Duration duration = Duration.between(
                this.localTime,
                time.getLocalTime()
        );

        return (int) duration.getSeconds();
    }

    public String toAMPM() {
        String time = this.localTime.format(DateTimeFormatter.ofPattern(FORMAT_TIME_AMPM));

        time = time.replace("a. m.", "AM");
        time = time.replace("p. m.", "PM");
        return time;
    }

    @Override
    public String toString() {
        return this.localTime.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Time("03:30 a.m.").toAMPM());
        System.out.println(new Time(3, 30));
        LocalTime local = LocalTime.of(3, 30);
        System.out.println(local.format(DateTimeFormatter.ofPattern("hh:mm a")));
    }
}
