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
@Table(name = "receiver_person")
public class ReceiverPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receiver_person")
    private Integer id;

    private String code;

    @Column(name = "id_group")
    private Integer idGroup;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy="receiverPerson")
    private List<Attendance> attendances;
}
