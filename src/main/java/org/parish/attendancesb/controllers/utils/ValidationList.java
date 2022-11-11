package org.parish.attendancesb.controllers.utils;

import java.util.Arrays;
import java.util.Optional;

public class ValidationList {

    public static boolean isValid(Validation... validations) {
        Optional<Validation> fieldInvalid = Arrays
                .stream(validations)
                .findAny()
                .filter(validation -> !validation.getValid());

        if (fieldInvalid.isEmpty())
            return true;

        fieldInvalid.ifPresent(validation -> Alert.warning(validation.getWarnning()));
        return false;
    }
}
