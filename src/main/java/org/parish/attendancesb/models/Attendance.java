package org.parish.attendancesb.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.parish.attendancesb.models.datetime.DateTime;

import javax.persistence.*;

@Data
@ToString
@RequiredArgsConstructor
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    @AttributeOverrides( {
        @AttributeOverride(name="dateTime", column = @Column(name="date_time") )
    })
    private DateTime dateTime;

    @ManyToOne()
    @JoinColumn(name = "id_catequesis")
    private Catequesis catequesis;

    @ManyToOne()
    @JoinColumn(name = "id_receiver_person")
    private ReceiverPerson receiverPerson;

}
