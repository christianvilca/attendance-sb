package org.parish.attendancesb.models;

import lombok.*;
import org.parish.attendancesb.exceptions.RemoveException;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Integer id;

    @NonNull
    @Column(name = "institution_name")
    private String institutionName;

    @NonNull
    private String name;

    @EqualsAndHashCode.Exclude
    @Column(columnDefinition = "TEXT")
    private String logo;

    public Institution(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return institutionName + " " + name;
    }
}
