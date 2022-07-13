package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.ReceiverPerson;

import java.util.Optional;

public interface CatequesisService {
    public Catequesis get();
    public Catequesis getById(Integer id);
}
