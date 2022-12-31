package org.parish.attendancesb.controllers.utils.validation;

import javafx.scene.control.ComboBox;

public class ValidationComboBox extends Validation {

    public ValidationComboBox(String field, ComboBox<?> comboBox) {
        super(field);
        setValid(comboBox);
    }

    private void setValid(ComboBox<?> comboBox) {
        if (comboBox.getSelectionModel().getSelectedItem() == null) {
            setWarnning("Please Select: " + getField());
            super.setValid(false);
            return;
        }
        super.setValid(true);
    }
}
