package org.parish.attendancesb.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.services.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.parish.attendancesb.models.datetime.Time;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CatequesisController extends RegistryController<Catequesis> {

    @FXML
    private ComboBox<String> day;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<Time> timeEnd;

    @FXML
    private ComboBox<Time> timeStart;

    @FXML
    private ComboBox<Integer> tolerance;

    private ObservableList<Time> timeList;

    @Autowired
    private CatequesisService service;

    public CatequesisController(CatequesisService service) {
        super(service);
        this.service = service;
    }

    @Override
    public void initializeObjects() {
        fillDays();
        fillTimes();
        fillTolerance();
    }

    private void fillDays() {
        day.getItems().addAll("Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado");
    }

    private void fillTimes() {
        this.timeList = FXCollections.observableArrayList();
        for (int i = 5; i <= 22; i++) {
            for (int j = 0; j <= 30; j += 30) {
                this.timeList.add(new Time(i, j));
            }
        }

        timeStart.getItems().addAll(this.timeList);
        timeEnd.getItems().addAll(this.timeList);
    }

    private void fillTolerance() {
        tolerance.getItems().addAll(5, 10, 15, 20, 25);
    }

    @Override
    public Catequesis getModelFromFields() {
        Catequesis catequesis = getCatequesis();

        catequesis.setName(name.getText());
        catequesis.setDay(day.getSelectionModel().getSelectedItem());
        catequesis.setTimeStart(timeStart.getSelectionModel().getSelectedItem());
        catequesis.setTimeEnd(timeEnd.getSelectionModel().getSelectedItem());
        catequesis.setTolerance(Integer.parseInt(tolerance.getValue().toString()));

        return catequesis;
    }

    private Catequesis getCatequesis() {
        if (registry == null)
            return new Catequesis();

        return registry;
    }

    @Override
    public void setFieldsFromModel() {
        this.name.setText(this.registry.getName());
        this.day.setValue(this.registry.getDay());
        this.timeStart.setValue(this.registry.getTimeStart());
        this.timeEnd.setValue(this.registry.getTimeEnd());
        this.tolerance.setValue(this.registry.getTolerance());
    }

    @Override
    public void clearFields() {
        name.clear();
        day.setValue(null);
        timeStart.setValue(null);
        timeEnd.setValue(null);
        tolerance.setValue(null);
    }
}
