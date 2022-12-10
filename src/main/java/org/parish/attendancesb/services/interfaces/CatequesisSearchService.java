package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.Catequesis;

import java.util.List;

public interface CatequesisSearchService  extends Service<Integer, Catequesis>{
    void setCatequesisList(List<Catequesis> catequesisList);
}
