package org.parish.attendancesb.models;

import lombok.*;
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
    @AttributeOverride(name = "localDateTime", column = @Column(name = "date_time", columnDefinition = "TEXT"))
    private DateTime dateTime;

    @ManyToOne()
    @JoinColumn(name = "catequesis_id")
    private Catequesis catequesis;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "receiver_person_id")
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
