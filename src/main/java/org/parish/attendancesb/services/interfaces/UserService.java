package org.parish.attendancesb.services.interfaces;

import org.parish.attendancesb.models.access.User;

public interface UserService extends Service<Integer, User> {

    boolean authenticate(String username, String password);

    User findByUsername(String username);

    boolean authorize(String role);
}
