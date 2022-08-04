package org.parish.attendancesb.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
@Entity
public class ReceiverPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String code;

    @ManyToOne()
    @JoinColumn(name="group_id")
    private Group group;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy="receiverPerson")
    private List<Attendance> attendances;
}
