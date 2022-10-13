package org.parish.attendancesb.services;

import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.repositories.RoleRepository;
import org.parish.attendancesb.repositories.UserRepository;
import org.parish.attendancesb.services.interfaces.PermissionService;
import org.parish.attendancesb.services.interfaces.RoleService;
import org.parish.attendancesb.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    private User user;

    @Override
    public boolean authenticate(String username, String password) {
        User userFound = this.findByUsername(username);
        System.out.println(userFound);
        if (userFound == null)
            return false;

        this.user = userFound;
        System.out.println(user.getRoles());

        return password.equals(userFound.getPassword());
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public boolean isAuthorize(String permissionName) {
        return user.getRoles().contains(
                roleService.findByPermissions(
                        permissionService.findByName(permissionName)
                )
        );
    }

    @Override
    public boolean authorize(String role) {
        System.out.println(user.getRoles());
        return user.getRoles().contains(
                roleService.findByName(role)
        );
    }

    @Override
    public User getById(Integer integer) {
        return null;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.findById(user.getId())
                .map(
                        u -> {
                            u.setGroup(user.getGroup());
                            u.setLastName(user.getLastName());
                            u.setFirstName(user.getFirstName());
                            u.setUsername(user.getUsername());
                            u.setPassword(user.getPassword());
                            u.setRoles(user.getRoles());
                            return this.save(u);
                        }
                ).orElse(null);
    }

    @Override
    public boolean contains(User user) {
        return repository.contains(user) && repository.findById(user.getId()).get().getRoles().equals(user.getRoles());
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public List<User> findByName(String name) {
        return repository.findByNameContaining(name);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
