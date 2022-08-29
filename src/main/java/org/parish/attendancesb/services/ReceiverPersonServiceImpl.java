package org.parish.attendancesb.services;

import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.repositories.ReceiverPersonRepository;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiverPersonServiceImpl implements ReceiverPersonService {

    private ReceiverPersonRepository repository;

    public ReceiverPersonServiceImpl(ReceiverPersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ReceiverPerson> findByCode(String code) {
        return repository.findByCode(code);
    }

    @Override
    public List<ReceiverPerson> findByName(String name) {
        return repository.findByNameContaining(name);
    }

    @Override
    public boolean contains(ReceiverPerson person) {
        return repository.contains(person);
    }

    @Override
    public ReceiverPerson getById(Integer id) {
        return repository.getById(id);

    @Override
    public boolean existsById(Integer integer) {
        return repository.existsById(integer);
    }

    @Override
    public ReceiverPerson save(ReceiverPerson receiverPerson) {
        return repository.save(receiverPerson);
    }

    @Override
    public void delete(ReceiverPerson receiverPerson) {
        repository.delete(receiverPerson);
    }

    @Override
    public ReceiverPerson update(ReceiverPerson receiverPerson) {
        return repository.findById(receiverPerson.getId())
                .map(
                        rp -> {
                            rp.setCode(receiverPerson.getCode());
                            rp.setFirstName(receiverPerson.getFirstName());
                            rp.setLastName(receiverPerson.getLastName());
                            rp.setGroup(receiverPerson.getGroup());
                            return this.save(rp);
                        }
                ).orElse(null);
    }

    @Override
    public List<ReceiverPerson> findAll() {
        return repository.findAll();
    }

}
