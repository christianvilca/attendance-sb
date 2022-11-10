package org.parish.attendancesb.models.access;

import lombok.*;
import org.parish.attendancesb.models.Catequesis;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Integer id;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @EqualsAndHashCode.Exclude
    @ManyToOne()
    @JoinColumn(name = "catequesis_id")
    private Catequesis catequesis;

    @Override
    public String toString() {
        return "Credential{" +
                ", user=" + user +
                ", role=" + role +
                ", catequesis=" + catequesis +
                '}';
    }

    public Credential(@NonNull User user, @NonNull Role role, Catequesis catequesis) {
        this.user = user;
        this.role = role;
        this.catequesis = catequesis;
    }
}
