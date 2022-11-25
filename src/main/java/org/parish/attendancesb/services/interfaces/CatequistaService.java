package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.models.access.User;

import java.util.List;

public interface CatequistaService extends Service<Integer, Catequista> {

    boolean existsById(Integer integer);

    void addUser(User user);

    List<User> getUserList();
}
