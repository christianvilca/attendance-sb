package org.parish.attendancesb.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@RequiredArgsConstructor
@Entity(name = "attendance_date")
public class AttendanceDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_catequesis")
    private Integer idCatequesis;

    private String date;

}
