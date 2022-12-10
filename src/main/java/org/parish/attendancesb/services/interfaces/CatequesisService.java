package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.access.User;

import java.util.List;

public interface CatequesisService extends Service<Integer, Catequesis> {

    List<Catequesis> findByName(String name);

    boolean contains(Catequesis catequesis);

    List<Catequesis> findByUser(User user);

    boolean hasManyByUser(User user);

    long countByUsers(User user);

    boolean hasOneByUser(User user);
}
