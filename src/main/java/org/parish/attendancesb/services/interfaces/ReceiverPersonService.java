package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.ReceiverPerson;

import java.util.Optional;

public interface ReceiverPersonService {

    public Optional<ReceiverPerson> getByCode(String code);
    public ReceiverPerson getById(Integer id);
}
