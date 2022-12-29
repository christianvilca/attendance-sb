package org.parish.attendancesb.models;

import lombok.*;
import org.parish.attendancesb.exceptions.RemoveException;

import javax.persistence.*;
import java.util.Set;

@Data
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
    private String color;

    @EqualsAndHashCode.Exclude
    @Column(columnDefinition = "TEXT")
    private String logo;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "catequesis_id")
    private Catequesis catequesis;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Catequista> catequistas;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "group")
    private Set<ReceiverPerson> receiverPeople;

    @PreRemove
    public void preRemove() {
        if (!this.catequistas.isEmpty()) {
            throw new RemoveException("No puede eliminar un Grupo que tiene Catequista.");
        }
        if (!this.receiverPeople.isEmpty()) {
            throw new RemoveException("No puede eliminar un Grupo que tiene persona de catequesis.");
        }
    }

    public Group(Integer id) {
        this.id = id;
    }

    public Group(Integer id, Catequesis catequesis) {
        this.id = id;
        this.catequesis = catequesis;
    }

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
