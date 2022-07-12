package org.parish.attendancesb.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@ToString
@RequiredArgsConstructor
@Entity
public class Catequesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String day;

    @Column(name = "hour_start")
    private String hourStart;

    @Column(name = "hour_end")
    private String hourEnd;

    private String tolerance;

    @OneToMany(mappedBy="catequesis")
    private List<Group> groups;

    @OneToMany(mappedBy="catequesis")
    private List<Attendance> attendances;
}