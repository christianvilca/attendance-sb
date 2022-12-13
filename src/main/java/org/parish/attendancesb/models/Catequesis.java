package org.parish.attendancesb.models;

import lombok.*;
import org.parish.attendancesb.exceptions.RemoveException;
import org.parish.attendancesb.models.datetime.Time;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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

    @NonNull
    private String receiverPersonType;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Catequista> catequistas;

    @PreRemove
    public void preRemove() {
        if (!this.catequistas.isEmpty()) {
            throw new RemoveException("No puede eliminar una Catequesis que tiene Catequista.");
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