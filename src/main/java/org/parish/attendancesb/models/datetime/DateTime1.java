package org.parish.attendancesb.models.datetime;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
public class DateTime1 {
    private LocalDateTime localDateTime;

    //private String dateTime;

    private static final String[] MONTHS = new String[]{
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre"
    };

    private static final String FORMAT_TIME_AMPM = "hh:mm a";

    public DateTime1() {
        this.localDateTime = LocalDateTime.now();
    }

    public DateTime1(String dateTime) {
        this.localDateTime = LocalDateTime.parse(dateTime);
    }

    public String getMonth() {
        return MONTHS[this.getMonthValue() - 1];
    }

    public int getMonthValue() {
        return this.localDateTime.getMonthValue();
    }

    public int getDay() {
        return this.localDateTime.getDayOfMonth();
    }

    public String getTimeAMPM() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TIME_AMPM);
        String hour = this.localDateTime.format(formatter);
        if (hour.contains("AM") || hour.contains("PM")) {
            return hour;
        }

        hour = hour.replace("a. m.", "AM");
        hour = hour.replace("p. m.", "PM");
        return hour;
    }

    public boolean isAfter(String dateTime) {
        return this.localDateTime.isAfter(LocalDateTime.parse(dateTime));
    }
}
