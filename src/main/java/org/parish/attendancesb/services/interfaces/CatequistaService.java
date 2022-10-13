package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.models.ReceiverPerson;

import java.util.Optional;

public interface CatequistaService extends Service<Integer, Catequista> {

    boolean existsById(Integer integer);

}
