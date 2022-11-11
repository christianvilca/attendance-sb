package org.parish.attendancesb.controllers.utils;

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

        fieldInvalid.ifPresent(validation -> Alert.warning(validation.getWarnning()));
        return false;
    }
}
