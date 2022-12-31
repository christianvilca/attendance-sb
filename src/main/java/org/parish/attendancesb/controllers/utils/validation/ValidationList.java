package org.parish.attendancesb.controllers.utils.validation;

import org.parish.attendancesb.controllers.utils.alert.AlertFx;

import java.util.Arrays;
import java.util.Optional;

public class ValidationList {

    private ValidationList() {}

    public static boolean isValid(Validation... validations) {
        Optional<Validation> fieldInvalid = Arrays
                .stream(validations)
                .filter(validation ->!validation.getValid())
                .findAny();

        if (fieldInvalid.isEmpty())
            return true;

        fieldInvalid.ifPresent(validation -> AlertFx.warning(validation.getWarnning()));
        return false;
    }
}
