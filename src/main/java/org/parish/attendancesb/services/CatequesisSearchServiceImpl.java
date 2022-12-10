package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.services.interfaces.CatequesisSearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatequesisSearchServiceImpl implements CatequesisSearchService {

    List<Catequesis> catequesisList = new ArrayList<>();

    @Override
    public Catequesis getById(Integer integer) {
        return null;
    }

    @Override
    public Catequesis save(Catequesis o) {
        return null;
    }

    @Override
    public Catequesis update(Catequesis o) {
        return null;
    }

    @Override
    public boolean contains(Catequesis o) {
        return false;
    }

    @Override
    public void delete(Catequesis o) {

    }

    @Override
    public List<Catequesis> findByName(String name) {
        return null;
    }

    @Override
    public List<Catequesis> findAll() {
        return catequesisList;
    }

    @Override
    public void setCatequesisList(List<Catequesis> catequesisList) {
        this.catequesisList = catequesisList;
    }

}
