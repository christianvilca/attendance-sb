package org.parish.attendancesb.services;

import lombok.Data;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.models.access.User;
import org.springframework.stereotype.Component;

@Component
@Data
public class SessionService {
    private User user;
    private Catequista catequista;
    private Catequesis catequesis;
}
