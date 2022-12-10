package org.parish.attendancesb.services;

import lombok.Data;
import org.parish.attendancesb.models.ReceiverPersonType;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Data
public class SessionService {
    private User user;
    private Catequista catequista;
    private Catequesis catequesis;

    private ReceiverPersonType getReceiverPersonType(){
        ReceiverPersonType receiverPersonType = Arrays.stream(ReceiverPersonType.values())
                .filter(a -> a.getPlural().equals(
                        catequesis.getReceiverPersonType())
                )
                .findFirst().orElse(null);
        assert receiverPersonType != null;
        return receiverPersonType;
    }

    public String getReceiverPersonTypeSingular(){
        return getReceiverPersonType().getSingular();
    }

    public String getReceiverPersonTypePlural(){
        return getReceiverPersonType().getPlural();
    }
}
