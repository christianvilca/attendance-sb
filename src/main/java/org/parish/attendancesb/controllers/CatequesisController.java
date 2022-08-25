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
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.springframework.beans.factory.annotation.Autowired;

import org.parish.attendancesb.models.datetime.Time;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CatequesisController implements Initializable {

    @FXML
    private Button btnSave;

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

    private Catequesis catequesis;

    @FXML
    void save(ActionEvent event) {
        Catequesis catequesis = this.getModelFromFields(new Catequesis());

        if (this.service.contains(catequesis)) {
            Alert.error("El registro ya existe!");
            return;
        }

        if (this.catequesis != null) {
            this.catequesis = this.getModelFromFields(this.catequesis);
            this.service.update(this.catequesis);
        } else {
            this.catequesis = catequesis;
            this.service.save(this.catequesis);
        }

        Alert.information("Se ha guardado correctamente!");
        this.close();

    }

    @FXML
    void cancel(ActionEvent event) {
        this.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fillDays();
        fillTimes();
        fillTolerance();

        if (catequesis != null)
            setFieldsFromModel();
        else
            clearFields();
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

    public Catequesis getModel() {
        return this.catequesis;
    }

    public void setModel(Catequesis catequesis) {
        this.catequesis = catequesis;
    }

    private void close() {
        Stage stage = (Stage) this.btnSave.getScene().getWindow();
        stage.close();
    }

    private Catequesis getModelFromFields(Catequesis catequesis) {
        catequesis.setName(name.getText());
        catequesis.setDay(day.getSelectionModel().getSelectedItem());
        catequesis.setTimeStart(timeStart.getSelectionModel().getSelectedItem());
        catequesis.setTimeEnd(timeEnd.getSelectionModel().getSelectedItem());
        catequesis.setTolerance(Integer.parseInt(tolerance.getValue().toString()));

        return catequesis;
    }

    private void setFieldsFromModel() {
        this.name.setText(this.catequesis.getName());
        this.day.setValue(this.catequesis.getDay());
        this.timeStart.setValue(this.catequesis.getTimeStart());
        this.timeEnd.setValue(this.catequesis.getTimeEnd());
        this.tolerance.setValue(this.catequesis.getTolerance());
    }

    private void clearFields() {
        name.clear();
        day.setValue(null);
        timeStart.setValue(null);
        timeEnd.setValue(null);
        tolerance.setValue(null);
    }
}
