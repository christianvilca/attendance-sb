package org.parish.attendancesb.models.access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;
}
