package org.parish.attendancesb.services;

import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.repositories.ReceiverPersonRepository;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceiverPersonServiceImpl implements ReceiverPersonService {

    private ReceiverPersonRepository receiverPersonRepository;
    
    @Override
    public Optional<ReceiverPerson> getByCode(String code) {
        Optional<ReceiverPerson> optReceiverPerson = receiverPersonRepository.findByCode(code);
        ReceiverPerson receiverPerson = optReceiverPerson.get();

        return Optional.of(receiverPerson);
    }

    @Override
    public ReceiverPerson getById(Integer id) {
        return null;
    }
}
