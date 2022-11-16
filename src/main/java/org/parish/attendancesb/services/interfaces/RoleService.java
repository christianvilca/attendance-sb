package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.access.Role;

public interface RoleService {

    Role findByName(String name);
}
