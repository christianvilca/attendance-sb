package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.access.Permission;
import org.parish.attendancesb.models.access.Role;

public interface RoleService {

    Role findByPermissions(Permission permissions);

    Role findByName(String name);
}
