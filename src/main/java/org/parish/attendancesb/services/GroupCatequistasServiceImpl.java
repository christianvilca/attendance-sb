package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.services.interfaces.GroupCatequistaService;
import org.parish.attendancesb.services.interfaces.GroupService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class GroupCatequistasServiceImpl implements GroupCatequistaService {

    private SessionService sessionService;
    private GroupService groupService;

    public GroupCatequistasServiceImpl(
            GroupService groupService,
            SessionService sessionService
    ) {
        this.groupService = groupService;
        this.sessionService = sessionService;
    }

    @Override
    public Catequista getById(Integer integer) {
        return null;
    }

    @Override
    public Catequista save(Catequista o) {
        return null;
    }

    @Override
    public Catequista update(Catequista o) {
        return null;
    }

    @Override
    public boolean contains(Catequista o) {
        return false;
    }

    @Override
    public void delete(Catequista o) {
    }

    @Override
    public List<Catequista> findByName(String name) {
        return this.findAll().stream().filter(catequista -> catequista.getFirstName().contains(name) || catequista.getLastName().contains(name)).collect(Collectors.toList());
    }

    @Override
    public List<Catequista> findAll() {
        List<Catequista> catequistas = new ArrayList<>();
        groupService.findAll().stream().forEach(group -> catequistas.addAll(group.getCatequistas()));

        List<Catequista> catequistas1 = new ArrayList<>(sessionService.getCatequesis().getCatequistas());
        catequistas1.removeAll(catequistas);
        return catequistas1;
    }

}
