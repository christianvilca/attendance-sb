package org.parish.attendancesb.models;

import lombok.*;
import org.parish.attendancesb.models.access.User;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Catequista {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "group")
    private List<ReceiverPerson> receiverPeople;

    public Catequista(Integer id) {
        this.id = id;
    }

    public Catequista(Integer id, Group group) {
        this.id = id;
        this.group = group;
    }

    @Override
    public String toString() {
        return firstName + ", " + lastName;
    }
}
