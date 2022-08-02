package org.parish.attendancesb.models;

import lombok.*;
import org.parish.attendancesb.models.datetime.Date;

import javax.persistence.*;

@Data
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class AttendanceDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Embedded
    @ManyToOne
    @JoinColumn(name="catequesis_id")
    private Catequesis catequesis;

    @NonNull
    @Embedded
    @AttributeOverride(name = "localDate", column = @Column(name = "date", columnDefinition = "TEXT"))
    private Date date;

    public String getMonth() {
        return this.date.getMonth();
    }

    public int getDay() {
        return this.date.getDay();
    }
}
