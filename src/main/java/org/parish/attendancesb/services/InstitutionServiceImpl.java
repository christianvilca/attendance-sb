package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Institution;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.repositories.InstitutionRepository;
import org.parish.attendancesb.repositories.ReceiverPersonRepository;
import org.parish.attendancesb.services.interfaces.InstitutionService;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    private InstitutionRepository repository;

    private SessionService sessionService;

    public InstitutionServiceImpl(InstitutionRepository repository, SessionService sessionService) {
        this.repository = repository;
        this.sessionService = sessionService;
    }

    @Override
    public List<Institution> findByName(String name) {
        return null; //repository.findByName(name);
    }

    @Override
    public List<Institution> findAll() {
        return null;
    }

    @Override
    public boolean contains(Institution person) {
        return repository.contains(person);
    }

    @Override
    public Institution getById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Institution getRegistry() {
        return repository.findAll().stream().findFirst().orElse(null);
    }

    @Override
    public boolean existsById(Integer integer) {
        return repository.existsById(integer);
    }

    @Override
    public Institution save(Institution institution) {
        return repository.save(institution);
    }

    @Override
    public void delete(Institution institution) {
        repository.delete(institution);
    }

    @Override
    public Institution update(Institution institution) {
        return repository.findById(institution.getId())
                .map(
                        rp -> {
                            rp.setInstitutionName(institution.getInstitutionName());
                            rp.setName(institution.getName());
                            rp.setLogo(institution.getLogo());
                            return this.save(rp);
                        }
                ).orElse(null);
    }

}
