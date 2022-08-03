package org.parish.attendancesb.models;

import lombok.*;
import org.parish.attendancesb.models.datetime.Time;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Entity
@Component
@Embeddable
public class Catequesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String day;

    @Embedded
    @AttributeOverride(name = "localTime", column = @Column(name = "time_start", columnDefinition = "TEXT"))
    private Time timeStart;

    @Embedded
    @AttributeOverride(name = "localTime", column = @Column(name = "time_end", columnDefinition = "TEXT"))
    private Time timeEnd;

    private int tolerance;

    @OneToMany(mappedBy = "catequesis")
    private List<Group> groups;

    @OneToMany(mappedBy = "catequesis")
    private List<Attendance> attendances;

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
    
}