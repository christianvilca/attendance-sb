package org.parish.attendancesb.models;

import lombok.*;
import org.parish.attendancesb.models.datetime.Date;

import javax.persistence.*;

@Data
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(name = "attendance_date")
public class AttendanceDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_catequesis")
    @NonNull
    private Catequesis catequesis;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "localDate", column = @Column(name = "date"))
    })
    @NonNull
    private Date date;

    public String getMonth() {
        return this.date.getMonth();
    }

    public int getDay() {
        return this.date.getDay();
    }
}
