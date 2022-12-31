package org.parish.attendancesb.controllers.utils.validation;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationTextField extends Validation {

    public ValidationTextField(String field, TextField textField, ValidationType type) {
        super(field);
        setValid(textField, type);
    }

    private void setValid(TextField textField, ValidationType type) {
        if (isEmpty(textField.getText())) {
            setWarnning("Please Enter: " + getField());
            super.setValid(false);
            return;
        }

        if (!isValidPattern(textField.getText(), type.getPattern())) {
            setWarnning("Please Enter Valid: " + getField());
            super.setValid(false);
            return;
        }

        super.setValid(true);
    }

    private boolean isEmpty(String value) {
        return value.trim().equals("");
    }

    private boolean isValidPattern(String value, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(value);
        return m.find() && m.group().equals(value);
    }
}
