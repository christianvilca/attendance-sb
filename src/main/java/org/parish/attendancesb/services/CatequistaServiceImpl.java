package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.repositories.CatequistaRepository;
import org.parish.attendancesb.services.interfaces.CatequistaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatequistaServiceImpl implements CatequistaService {

    private CatequistaRepository repository;

    public CatequistaServiceImpl(CatequistaRepository repository) {
        this.repository = repository;
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
        return repository.findById(id).get();
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
                            c.setGroups(catequista.getGroups());
                            return this.save(c);
                        }
                ).orElse(null);
    }

    @Override
    public List<Catequista> findAll() {
        return repository.findAll();
    }

}
