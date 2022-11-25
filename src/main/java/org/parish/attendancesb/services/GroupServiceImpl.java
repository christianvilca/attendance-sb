package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.repositories.GroupRepository;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupRepository repository;

    private SessionService sessionService;

    public GroupServiceImpl(GroupRepository repository, SessionService sessionService) {
        this.repository = repository;
        this.sessionService = sessionService;
    }

    @Override
    public List<Group> findAllByName(String name) {
        return repository.findAllByNameContaining(name);
    }

    @Override
    public List<Group> findAllByCatequesis(Catequesis catequesis) {
        return repository.findAllByCatequesis(catequesis);
    }

    @Override
    public boolean contains(Group group) {
        return repository.contains(group);
    }

    @Override
    public Group getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public Group save(Group group) {
        return repository.save(group);
    }

    @Override
    public void delete(Group group) {
        repository.delete(group);
    }

    @Override
    public List<Group> findByName(String name) {
        return repository.findByCatequesisAndNameContains(sessionService.getCatequesis(), name);
    }

    @Override
    public Group update(Group group) {
        return repository.findById(group.getId())
                .map(
                        g -> {
                            g.setName(group.getName());
                            g.setCatequesis(group.getCatequesis());
                            g.setCatequistas(group.getCatequistas());
                            return repository.save(g);
                        }
                ).orElse(null);
    }

    @Override
    public List<Group> findAll() {
        return repository.findAllByCatequesis(sessionService.getCatequesis());
    }
}
