package org.parish.attendancesb.services;


import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.repositories.RoleRepository;
import org.parish.attendancesb.repositories.UserRepository;
import org.parish.attendancesb.services.interfaces.RoleService;
import org.parish.attendancesb.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    private User user;

    @Override
    public boolean authenticate(String username, String password) {
        User userFound = this.findByUsername(username);

        if (userFound == null)
            return false;

        this.user = userFound;

        return password.equals(userFound.getPassword());
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public boolean authorize(String role) {
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
        return repository.findByUsernameContaining(name);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
