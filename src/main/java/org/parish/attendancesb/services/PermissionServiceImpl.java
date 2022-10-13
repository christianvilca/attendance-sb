package org.parish.attendancesb.services;

import org.parish.attendancesb.models.access.Permission;
import org.parish.attendancesb.repositories.PermissionRepository;
import org.parish.attendancesb.services.interfaces.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository repository;

    @Override
    public Permission findByName(String name) {
        return repository.findByName(name);
    }
}
