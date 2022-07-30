package org.parish.attendancesb.models;

import lombok.*;
import org.parish.attendancesb.models.datetime.Date;
import org.parish.attendancesb.models.datetime.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Component
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "dateTime", column = @Column(name = "date_time"))
    })
    @NonNull
    private DateTime dateTime;

    @ManyToOne()
    @JoinColumn(name = "id_catequesis")
    private Catequesis catequesis;

    @ManyToOne()
    @JoinColumn(name = "id_receiver_person")
    @NonNull
    private ReceiverPerson receiverPerson;

    public Attendance(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Attendance(DateTime dateTime, Catequesis catequesis) {
        this.dateTime = dateTime;
        this.catequesis = catequesis;
    }

    public boolean isBeforeStart() {
        DateTime dateTime = new DateTime(this.dateTime.getDate(), catequesis.getTimeStartWithTolerance());
        return this.dateTime.isBefore(dateTime);
    }

    public boolean isAfterStart() {
        DateTime dateTime = new DateTime(this.dateTime.getDate(), catequesis.getTimeStartWithTolerance());
        return this.dateTime.isAfter(dateTime);
    }

    public boolean isBeforeMiddle() {
        DateTime dateTime = new DateTime(this.dateTime.getDate(), catequesis.getTimeMiddle());
        return this.dateTime.isBefore(dateTime);
    }

    public boolean isAfterMiddle() {
        DateTime dateTime = new DateTime(this.dateTime.getDate(), catequesis.getTimeMiddle());
        return this.dateTime.isAfter(dateTime);
    }

    public String toAMPM() {
        return this.dateTime.getTime().toAMPM();
    }

}
