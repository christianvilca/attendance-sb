package org.parish.attendancesb.services;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.repositories.CatequesisRepository;
import org.parish.attendancesb.services.catequesis.SessionSingleton;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatequesisServiceImpl implements CatequesisService {

    private CatequesisRepository catequesisRepository;

    public CatequesisServiceImpl(CatequesisRepository catequesisRepository) {
        this.catequesisRepository = catequesisRepository;
    }

    @Override
    public Catequesis get() {
        return this.catequesisRepository.getById(SessionSingleton.instance().getIdCatequesis());
    }

    public List<Catequesis> getAllCatequesis() {
        return catequesisRepository.findAll();
    }

    public Catequesis save(Catequesis newCatequesis) {
        return catequesisRepository.save(newCatequesis);
    }

    public void delete(int id) {
        catequesisRepository.delete(new Catequesis(id));
    }

    public Catequesis update(Catequesis newCatequesis, int id) {
        return catequesisRepository.findById(id)
                .map(
                        Catequesis -> {
                            Catequesis.setName(newCatequesis.getName());
                            Catequesis.setDay(newCatequesis.getDay());
                            Catequesis.setTimeStart(newCatequesis.getTimeStart());
                            Catequesis.setTimeEnd(newCatequesis.getTimeEnd());
                            Catequesis.setTolerance(newCatequesis.getTolerance());
                            return catequesisRepository.save(Catequesis);
                        }
                ).orElse(null);
    }
}
