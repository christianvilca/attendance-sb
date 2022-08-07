package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.Catequesis;

import java.util.List;

public interface CatequesisService extends Service<Integer, Catequesis> {

    public Catequesis get();

    public List<Catequesis> findByName(String name);

}
