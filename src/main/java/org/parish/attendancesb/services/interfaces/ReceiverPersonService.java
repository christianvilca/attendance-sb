package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.ReceiverPerson;

import java.util.List;
import java.util.Optional;

public interface ReceiverPersonService extends Service<Integer, ReceiverPerson> {

    public Optional<ReceiverPerson> findByCode(String code);

    public List<ReceiverPerson> findByName(String name);

    public boolean contains(ReceiverPerson person);

}
