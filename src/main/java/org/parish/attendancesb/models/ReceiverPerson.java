package org.parish.attendancesb.models;

import lombok.*;
import org.parish.attendancesb.exceptions.RemoveException;

import javax.persistence.*;
import java.util.Set;

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

    @EqualsAndHashCode.Exclude
    @Column(columnDefinition = "TEXT")
    private String photo;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "group_id")
    @EqualsAndHashCode.Exclude
    private Group group;

    @EqualsAndHashCode.Exclude
    @Column(columnDefinition = "TEXT")
    private String carnetFront;

    @EqualsAndHashCode.Exclude
    @Column(columnDefinition = "TEXT")
    private String carnetBack;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "receiverPerson", fetch = FetchType.EAGER)
    private Set<Attendance> attendances;

    @PreRemove
    public void preRemove() {
        if (!this.attendances.isEmpty()) {
            throw new RemoveException("No puede eliminar una Persona que tiene Asistencia.");
        }
    }

    public ReceiverPerson(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
