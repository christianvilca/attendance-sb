package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Group;

import java.util.List;

public interface GroupService extends Service<Integer, Group> {

    public List<Group> findAllByName(String name);

    public List<Group> findAllByCatequesis(Catequesis catequesis);

    boolean contains(Group group);
}
