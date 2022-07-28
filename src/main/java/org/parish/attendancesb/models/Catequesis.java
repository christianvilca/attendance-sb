package org.parish.attendancesb.models;

import lombok.*;
import org.parish.attendancesb.models.datetime.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Entity
@Component
public class Catequesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String day;

    @Column(name = "hour_start")
    private Time timeStart;

    @Column(name = "hour_end")
    private Time timeEnd;

    private int tolerance;

    @OneToMany(mappedBy = "catequesis")
    private List<Group> groups;

    @OneToMany(mappedBy = "catequesis")
    private List<Attendance> attendances;

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