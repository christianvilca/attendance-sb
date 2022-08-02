package org.parish.attendancesb.models.datetime;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@ToString
@Component
@Embeddable
public class DateTime {
    private LocalDateTime localDateTime;

    private static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    public DateTime() {
        this.localDateTime = LocalDateTime.now();
    }

    public DateTime(String dateTime) {
        this.localDateTime = LocalDateTime.parse(dateTime);
    }

    public DateTime(Date date, Time time) {
        localDateTime = LocalDateTime.of(date.getLocalDate(), time.getLocalTime());
    }

    public boolean isBefore(String dateTime) {
        return this.localDateTime.isBefore(LocalDateTime.parse(dateTime));
    }

    public boolean isBefore(DateTime dateTime) {
        return this.localDateTime.isBefore(dateTime.getLocalDateTime());
    }

    public boolean isAfter(String dateTime) {
        return this.localDateTime.isAfter(LocalDateTime.parse(dateTime));
    }

    public boolean isAfter(DateTime dateTime) {
        return this.localDateTime.isAfter(dateTime.getLocalDateTime());
    }

    public Date getDate() {
        return new Date(
                localDateTime.getYear(),
                localDateTime.getMonthValue(),
                localDateTime.getDayOfMonth()
        );
    }

    public Time getTime() {
        return new Time(
                localDateTime.getHour(),
                localDateTime.getMinute(),
                localDateTime.getSecond()
        );
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 01, 01, 5, 30, 20);
        LocalDateTime localDateTime1 = LocalDateTime.of(2022, 01, 01, 8, 6);
        System.out.println(LocalDateTime.now());
        System.out.println(localDateTime);
        System.out.println(localDateTime1);
    }
}
