package org.parish.attendancesb.models.access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.parish.attendancesb.exceptions.RemoveException;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Catequista;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Integer id;

    @NonNull
    private String username;

    @EqualsAndHashCode.Exclude
    @NonNull
    private String password;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Catequesis> catequeses;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "user")
    private Catequista catequista;

    @PreRemove
    public void preRemove() {
        if (catequista != null && catequista.getUser() != null) {
            throw new RemoveException("No puedes eliminar un Usuario asociado a un Catequista!");
        }
        if (!this.catequeses.isEmpty()) {
            throw new RemoveException("No puede eliminar un Usuario que tiene Catequesis.");
        }
    }

    @Override
    public String toString() {
        return username;
    }
}
