package org.parish.attendancesb.services;

import org.parish.attendancesb.models.access.Role;
import org.parish.attendancesb.repositories.RoleRepository;
import org.parish.attendancesb.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public Role findByName(String name) {
        return repository.findByName(name);
    }
}
