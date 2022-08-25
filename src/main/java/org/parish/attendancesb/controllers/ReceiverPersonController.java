package org.parish.attendancesb.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ReceiverPersonController implements Initializable {

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<Group> group;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @Autowired
    private ReceiverPersonService service;

    @Autowired
    private GroupService groupService;

    private ReceiverPerson person;

    @FXML
    void save(ActionEvent event) {
        ReceiverPerson person = this.getModelFromFields(new ReceiverPerson());

        if (this.service.contains(person)) {
            Alert.error("El registro ya existe!");
            return;
        }

        if (this.person != null) {
            this.person = this.getModelFromFields(this.person);
            this.service.update(this.person);
        } else {
            this.person = person;
            this.service.save(this.person);
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
        fillGroups();

        if (person != null)
            setFieldsFromModel();
        else
            clearFields();
    }

    private void fillGroups() {
        group.getItems().addAll(groupService.findAll());
    }

    public ReceiverPerson getModel() {
        return this.person;
    }

    public void setModel(ReceiverPerson person) {
        this.person = person;
    }

    private void close() {
        Stage stage = (Stage) this.btnSave.getScene().getWindow();
        stage.close();
    }

    private ReceiverPerson getModelFromFields(ReceiverPerson person) {
        person.setCode("00");
        person.setGroup(group.getSelectionModel().getSelectedItem());
        person.setFirstName(txtFirstName.getText());
        person.setLastName(txtLastName.getText());

        return person;
    }

    private void setFieldsFromModel() {
        this.group.setValue(this.person.getGroup());
        this.txtFirstName.setText(this.person.getFirstName());
        this.txtLastName.setText(this.person.getLastName());
    }

    private void clearFields() {
        group.setValue(null);
        txtFirstName.clear();
        txtLastName.clear();
    }
}
