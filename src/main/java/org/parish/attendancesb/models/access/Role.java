package org.parish.attendancesb.models.access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Integer id;

    @NonNull
    private String name;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Set<Permission> permissions;
}
