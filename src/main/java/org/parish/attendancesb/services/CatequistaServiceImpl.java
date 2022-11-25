package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.repositories.CatequistaRepository;
import org.parish.attendancesb.services.interfaces.CatequistaService;
import org.parish.attendancesb.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatequistaServiceImpl implements CatequistaService {

    private List<User> userList;

    private CatequistaRepository repository;

    private UserService userService;

    public CatequistaServiceImpl(
            CatequistaRepository repository,
            UserService userService
    ) {
        this.repository = repository;
        this.userService = userService;
        userList = new ArrayList<>();
    }

    @Override
    public List<Catequista> findByName(String name) {
        return repository.findByNameContaining(name);
    }

    @Override
    public boolean contains(Catequista person) {
        return repository.contains(person);
    }

    @Override
    public Catequista getById(Integer id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        return null;
    }

    @Override
    public boolean existsById(Integer integer) {
        return repository.existsById(integer);
    }

    @Override
    public Catequista save(Catequista catequista) {
        return repository.save(catequista);
    }

    @Override
    public void delete(Catequista catequista) {
        repository.delete(catequista);
    }

    @Override
    public Catequista update(Catequista catequista) {
        return repository.findById(catequista.getId())
                .map(
                        c -> {
                            c.setFirstName(catequista.getFirstName());
                            c.setLastName(catequista.getLastName());
                            c.setUser(catequista.getUser());
                            return this.save(c);
                        }
                ).orElse(null);
    }

    @Override
    public List<Catequista> findAll() {
        return repository.findAll();
    }

    @Override
    public List<User> getUserList() {
        List<User> users = userService.findAll();
        users.removeAll(
                this.findAll().stream()
                        .map(Catequista::getUser)
                        .collect(Collectors.toList())
        );
        users.addAll(userList);
        userList.clear();
        users.remove(null);

        users = users.stream()
                .sorted(Comparator.comparing(User::getUsername))
                .collect(Collectors.toList());

        users.add(0, null);

        return users;
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
    }
}
