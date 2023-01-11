package org.parish.attendancesb.services;

import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.repositories.ReceiverPersonRepository;
import org.parish.attendancesb.services.carnet.Carnet;
import org.parish.attendancesb.services.barcode.EAN13;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiverPersonServiceImpl implements ReceiverPersonService {

    private ReceiverPersonRepository repository;

    private SessionService sessionService;

    private Carnet carnet;

    public ReceiverPersonServiceImpl(ReceiverPersonRepository repository, SessionService sessionService, Carnet carnet) {
        this.repository = repository;
        this.sessionService = sessionService;
        this.carnet = carnet;
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
        return repository.findById(id).get();
    }

    @Override
    public boolean existsById(Integer integer) {
        return repository.existsById(integer);
    }

    @Override
    public String getCode() {
        Integer id = repository.getNextId();

        if (id == null)
            return EAN13.get(1);

        return EAN13.get(id);
    }

    @Override
    public ReceiverPerson save(ReceiverPerson receiverPerson) {
        carnet.setReceiverPerson(receiverPerson);
        carnet.draw();
        receiverPerson.setCarnetFront(carnet.getCarnetFront());
        receiverPerson.setCarnetBack(carnet.getCarnetBack());

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
                            rp.setFirstName(receiverPerson.getFirstName());
                            rp.setLastName(receiverPerson.getLastName());
                            rp.setGroup(receiverPerson.getGroup());
                            rp.setPhoto(receiverPerson.getPhoto());
                            return this.save(rp);
                        }
                ).orElse(null);
    }

    @Override
    public List<ReceiverPerson> findAll() {
        return repository.findByGroup_Catequesis(sessionService.getCatequesis());
    }

}
