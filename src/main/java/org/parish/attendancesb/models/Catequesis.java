package org.parish.attendancesb.models;

import lombok.*;
import org.parish.attendancesb.exceptions.RemoveException;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.models.datetime.Time;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Component
@Embeddable
public class Catequesis {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String day;

    @NonNull
    @Embedded
    @AttributeOverride(name = "localTime", column = @Column(name = "time_start", columnDefinition = "TEXT"))
    private Time timeStart;

    @NonNull
    @Embedded
    @AttributeOverride(name = "localTime", column = @Column(name = "time_end", columnDefinition = "TEXT"))
    private Time timeEnd;

    @NonNull
    private int tolerance;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Catequista> catequistas;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "catequesis")
    private List<Group> groups;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "catequesis")
    private List<Attendance> attendances;

    @PreRemove
    public void preRemove() {
        if (!this.users.isEmpty()) {
            throw new RemoveException("No puede eliminar una Catequesis que tiene Usuario.");
        }
        if (!this.catequistas.isEmpty()) {
            throw new RemoveException("No puede eliminar una Catequesis que tiene Catequista.");
        }
        if (!this.groups.isEmpty()) {
            throw new RemoveException("No puede eliminar un Catequesis que tiene Grupo.");
        }
        if (!this.attendances.isEmpty()) {
            throw new RemoveException("No puede eliminar un Catequesis que tiene Asistencias.");
        }
    }

    public Catequesis(Integer id) {
        this.id = id;
    }

    public Catequesis(Time timeStart, Time timeEnd, int tolerance) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.tolerance = tolerance;
    }

    public Time getTimeStartWithTolerance() {
        return this.timeStart.plusMinutes(this.tolerance);
    }

    public Time getTimeMiddle() {
        return this.timeStart.plusSeconds(this.timeStart.duration(this.timeEnd) / 2);
    }

    @Override
    public String toString() {
        return name;
    }

}