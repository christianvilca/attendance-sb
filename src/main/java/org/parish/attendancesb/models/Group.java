package org.parish.attendancesb.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
@Entity(name="group_catequesis")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @ManyToOne()
    @JoinColumn(name="catequesis_id")
    private Catequesis catequesis;

    @OneToMany(mappedBy = "group")
    private List<ReceiverPerson> receiverPeople;

}
