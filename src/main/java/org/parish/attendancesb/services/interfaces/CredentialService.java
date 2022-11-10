package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.access.Credential;
import org.parish.attendancesb.models.access.Role;
import org.parish.attendancesb.models.access.User;

import java.util.List;

public interface CredentialService extends Service<Integer, Credential> {

    Credential save(User user, Role role, Catequesis catequesis);

    List<Credential> findByUser(User user);
}
