package org.parish.attendancesb.services;

import org.parish.attendancesb.models.ReceiverPerson;

import java.util.Optional;

public interface ReceiverPersonService {

    public Optional<ReceiverPerson> getByCode(String code);
}
