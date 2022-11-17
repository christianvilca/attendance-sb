package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.repositories.CatequesisRepository;
import org.parish.attendancesb.services.catequesis.SessionSingleton;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatequesisServiceImpl implements CatequesisService {

    private CatequesisRepository repository;

    private GroupService groupService;

    private Catequesis catequesis;

    public CatequesisServiceImpl(CatequesisRepository repository, GroupService groupService) {
        this.repository = repository;
        this.groupService = groupService;
    }

    @Override
    public List<Catequesis> findByName(String name) {
        return repository.findByNameContaining(name);
    }

    @Override
    public boolean contains(Catequesis catequesis) {
        return repository.contains(catequesis);
    }

    @Override
    public Catequesis getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public Catequesis save(Catequesis catequesis) {
        Catequesis newCatequesis = repository.save(catequesis);

        Group group = new Group();
        group.setName("SIN GRUPO");
        group.setCatequesis(newCatequesis);
        if (!groupService.contains(group))
            groupService.save(group);

        return newCatequesis;
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
