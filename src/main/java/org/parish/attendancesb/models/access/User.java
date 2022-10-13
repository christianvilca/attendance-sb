package org.parish.attendancesb.models.access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.parish.attendancesb.models.Group;

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
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    private String username;

    @EqualsAndHashCode.Exclude
    @NonNull
    private String password;
//
//    @NonNull
//    private Boolean coordinator;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    //@EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
