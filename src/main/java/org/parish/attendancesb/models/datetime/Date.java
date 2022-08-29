package org.parish.attendancesb.models.datetime;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor
@Component
@Embeddable
public class Date {
    private LocalDate localDate;

    private static final String FORMAT_DATE = "yyyy-MM-dd";

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

    public Date(String date) {
        this.parse(date);
    }

    public Date(int year, int month, int day) {
        this.set(year, month, day);
    }

    public void set(int year, int month, int day) {
        localDate = LocalDate.of(year, month, day);
    }

    public void parse(String date) {
        localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(FORMAT_DATE));
    }

    public int getYear() {
        return this.localDate.getYear();
    }

    public String getMonth() {
        return MONTHS[this.getMonthValue() - 1];
    }

    public int getMonthValue() {
        return this.localDate.getMonthValue();
    }

    public int getDay() {
        return this.localDate.getDayOfMonth();
    }

    public String getDate() {
        return localDate.toString();
    }

    @Override
    public String toString() {
        return localDate.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Date("2022-10-15"));
    }
}
