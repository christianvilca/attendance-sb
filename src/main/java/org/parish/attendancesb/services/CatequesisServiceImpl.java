package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.repositories.CatequesisRepository;
import org.parish.attendancesb.services.catequesis.SessionSingleton;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatequesisServiceImpl implements CatequesisService {

    private CatequesisRepository repository;

    public CatequesisServiceImpl(CatequesisRepository repository) {
        this.repository = repository;
    }

    @Override
    public Catequesis get() {
        return this.getById(SessionSingleton.instance().getIdCatequesis());
    }

    @Override
    public List<Catequesis> findByName(String name) {
        return repository.findByNameContaining(name);
    }

    @Override
    public Catequesis getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public Catequesis save(Catequesis catequesis) {
        return repository.save(catequesis);
    }

    @Override
    public void delete(Catequesis catequesis) {
        repository.delete(catequesis);
    }

    @Override
    public Catequesis update(Catequesis catequesis) {
        return repository.findById(catequesis.getId())
                .map(
                        c -> {
                            c.setName(catequesis.getName());
                            c.setDay(catequesis.getDay());
                            c.setTimeStart(catequesis.getTimeStart());
                            c.setTimeEnd(catequesis.getTimeEnd());
                            c.setTolerance(catequesis.getTolerance());
                            return this.save(c);
                        }
                ).orElse(null);
    }

    @Override
    public List<Catequesis> findAll() {
        return repository.findAll();
    }

}
