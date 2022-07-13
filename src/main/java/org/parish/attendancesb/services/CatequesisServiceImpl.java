package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.repositories.CatequesisRepository;
import org.parish.attendancesb.services.catequesis.SessionSingleton;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.springframework.stereotype.Service;

@Service
public class CatequesisServiceImpl implements CatequesisService {
    private CatequesisRepository catequesisRepository;

    public CatequesisServiceImpl(CatequesisRepository catequesisRepository) {
        this.catequesisRepository = catequesisRepository;
    }

    @Override
    public Catequesis get(){
        return this.catequesisRepository.getById(SessionSingleton.instance().getIdCatequesis());
    }
}
