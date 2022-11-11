package org.parish.attendancesb.controllers.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Validation {

    private String field;
    private String warnning;
    private boolean valid;

    public Validation(String field, ComboBox<?> comboBox) {
        this.field = field;
        setValid(comboBox);
    }

    public Validation(String field, TextField textField, ValidationType type) {
        this.field = field;
        setValid(textField, type);
    }

    private void setValid(ComboBox<?> comboBox) {
        if (comboBox.getSelectionModel().getSelectedItem() == null) {
            warnning = "Please Select: " + field;
            valid = false;
        }
    }

    private void setValid(TextField textField, ValidationType type) {
        if (isEmpty(textField.getText())) {
            warnning = "Please Enter: " + field;
            valid = false;
        }

        if (isValidPattern(textField.getText(), type.getPattern())) {
            warnning = "Please Enter Valid: " + field;
            valid = false;
        }
    }

    public boolean getValid() {
        return valid;
    }

    public String getWarnning(){
        return warnning;
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
