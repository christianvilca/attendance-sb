package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.Institution;
import org.parish.attendancesb.models.ReceiverPerson;

import java.util.Optional;

public interface InstitutionService extends Service<Integer, Institution> {
    Institution getRegistry();
    boolean existsById(Integer integer);

}
