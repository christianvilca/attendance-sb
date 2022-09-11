package org.parish.attendancesb.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class ReceiverPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Integer id;

    @NonNull
    private String code;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "group_id")
    @EqualsAndHashCode.Exclude
    private Group group;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "receiverPerson", fetch = FetchType.EAGER)
    private List<Attendance> attendances;

    public ReceiverPerson(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return firstName + ", " + lastName;
    }
}
