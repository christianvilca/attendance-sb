package org.parish.attendancesb.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "group_catequesis")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "catequesis_id")
    private Catequesis catequesis;

    @OneToMany(mappedBy = "group")
    private List<ReceiverPerson> receiverPeople;

    public Group(Integer id) {
        this.id = id;
    }

    public Group(Integer id, Catequesis catequesis) {
        this.id = id;
        this.catequesis = catequesis;
    }
}
