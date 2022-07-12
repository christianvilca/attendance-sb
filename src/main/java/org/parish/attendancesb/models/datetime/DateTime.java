package org.parish.attendancesb.models.datetime;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class DateTime {
    private String dateTime;

    public DateTime() {
        this.dateTime = LocalDateTime.now().toString();
    }

    public DateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime).toString();
    }
}
