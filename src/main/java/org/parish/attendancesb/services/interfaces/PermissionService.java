package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.access.Permission;

public interface PermissionService {
    Permission findByName(String name);
}
