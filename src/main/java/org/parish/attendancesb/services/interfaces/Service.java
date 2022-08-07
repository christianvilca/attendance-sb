package org.parish.attendancesb.services.interfaces;

import java.util.List;

public interface Service<K, O> {
    public O getById(K k);

    public O save(O o);

    public O update(O o);

    public void delete(O o);

    public List<O> findAll();
}
