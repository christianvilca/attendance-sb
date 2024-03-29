package org.parish.attendancesb.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.controllers.utils.validation.ValidationComboBox;
import org.parish.attendancesb.controllers.utils.validation.ValidationList;
import org.parish.attendancesb.controllers.utils.validation.ValidationTextField;
import org.parish.attendancesb.controllers.utils.validation.ValidationType;
import org.parish.attendancesb.models.ReceiverPersonType;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.services.interfaces.CatequesisService;

import org.parish.attendancesb.models.datetime.Time;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CatequesisController extends RegistryController<Catequesis> {

    @FXML
    private TextField name;

    @FXML
    private ComboBox<String> day;

    @FXML
    private ComboBox<Time> timeStart;

    @FXML
    private ComboBox<Time> timeEnd;

    @FXML
    private ComboBox<Integer> tolerance;

    @FXML
    private ComboBox<String> receiverPersonType;

    public CatequesisController(CatequesisService service) {
        super(service);
    }

    @Override
    public void initializeObjects() {
        fillDays();
        fillTimes();
        fillTolerance();
        fillAddressed();
    }

    private void fillDays() {
        day.getItems().addAll("Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado");
    }

    private void fillTimes() {
        ObservableList<Time> timeList;

        timeList = FXCollections.observableArrayList();
        for (int i = 5; i <= 22; i++) {
            for (int j = 0; j <= 30; j += 30) {
                timeList.add(new Time(i, j));
            }
        }

        timeStart.getItems().addAll(timeList);
        timeEnd.getItems().addAll(timeList);
    }

    private void fillTolerance() {
        tolerance.getItems().addAll(5, 10, 15, 20, 25);
    }

    private void fillAddressed() {
        receiverPersonType.getItems().addAll(
                Arrays.stream(ReceiverPersonType.values()).map(ReceiverPersonType::getPlural).collect(Collectors.toList())
        );
    }

    @Override
    public Catequesis getModelFromFields() {
        Catequesis catequesis = getCatequesis();

        catequesis.setName(name.getText());
        catequesis.setDay(day.getSelectionModel().getSelectedItem());
        catequesis.setTimeStart(timeStart.getSelectionModel().getSelectedItem());
        catequesis.setTimeEnd(timeEnd.getSelectionModel().getSelectedItem());
        catequesis.setTolerance(Integer.parseInt(tolerance.getValue().toString()));
        catequesis.setReceiverPersonType(receiverPersonType.getSelectionModel().getSelectedItem());

        return catequesis;
    }

    private Catequesis getCatequesis() {
        if (registry == null)
            return new Catequesis();

        return registry;
    }

    @Override
    public boolean isValid() {
        return ValidationList.isValid(
                new ValidationTextField("Nombre", name, ValidationType.ALPHANUMERIC),
                new ValidationComboBox("Día", day),
                new ValidationComboBox("Hora Inicial", timeStart),
                new ValidationComboBox("Hora Final", timeEnd),
                new ValidationComboBox("Tolerancia", tolerance),
                new ValidationComboBox("Dirigido a", receiverPersonType)
        );
    }

    @Override
    public void setFieldsFromModel() {
        this.name.setText(this.registry.getName());
        this.day.setValue(this.registry.getDay());
        this.timeStart.setValue(this.registry.getTimeStart());
        this.timeEnd.setValue(this.registry.getTimeEnd());
        this.tolerance.setValue(this.registry.getTolerance());
        this.receiverPersonType.setValue(this.registry.getReceiverPersonType());
    }

    @Override
    public void clearFields() {
        name.clear();
        day.setValue(null);
        timeStart.setValue(null);
        timeEnd.setValue(null);
        tolerance.setValue(null);
        receiverPersonType.setValue(null);
    }
}
