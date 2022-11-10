package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.access.Credential;
import org.parish.attendancesb.models.access.Role;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.repositories.CatequistaRepository;
import org.parish.attendancesb.repositories.CredentialRepository;
import org.parish.attendancesb.services.interfaces.CredentialService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialServiceImpl implements CredentialService {

    private CredentialRepository repository;

    public CredentialServiceImpl(CredentialRepository repository) {
        this.repository = repository;
    }

    @Override
    public Credential getById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Credential save(Credential credential) {
        if (credential.getId() == null)
            return repository.save(credential);

        return this.update(credential);
    }

    public Credential findByUserAndRoleAndCatequesis(User user, Role role, Catequesis catequesis) {
        if (repository.existsByUserAndRoleAndCatequesis(user, role, catequesis))
            return repository.findByUserAndRoleAndCatequesis(user, role, catequesis);

        return new Credential(user, role, catequesis);
    }

    @Override
    public Credential save(User user, Role role, Catequesis catequesis) {
        return this.save(
                this.findByUserAndRoleAndCatequesis(user, role, catequesis)
        );
    }

    @Override
    public List<Credential> findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public Credential update(Credential credential) {
        return repository.findById(credential.getId())
                .map(
                        c -> {
                            c.setUser(credential.getUser());
                            c.setRole(credential.getRole());
                            c.setCatequesis(credential.getCatequesis());
                            return repository.save(c);
                        }
                ).orElse(null);
    }

    @Override
    public boolean contains(Credential o) {
        return false;
    }

    @Override
    public void delete(Credential credential) {
        repository.delete(credential);
    }

    @Override
    public List<Credential> findByName(String name) {
        return null;
    }

    @Override
    public List<Credential> findAll() {
        return repository.findAll();
    }


}
