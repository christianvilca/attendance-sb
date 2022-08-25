package org.parish.attendancesb.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class GroupController implements Initializable {

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<Catequesis> catequesis;

    @FXML
    private TextField name;

    @Autowired
    private GroupService service;

    @Autowired
    private CatequesisService catequesisService;

    private Group group;

    @FXML
    void save(ActionEvent event) {
        Group group = this.getModelFromFields(new Group());

        if (this.service.contains(group)) {
            Alert.error("El registro ya existe!");
            return;
        }

        if (this.group != null) {
            this.group = this.getModelFromFields(this.group);
            this.service.update(this.group);
        } else {
            this.group = group;
            this.service.save(this.group);
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
        fillCatequeses();

        if (group != null)
            setFieldsFromModel();
        else
            clearFields();
    }

    private void fillCatequeses() {
        catequesis.getItems().addAll(catequesisService.findAll());
    }

    public Group getModel() {
        return this.group;
    }

    public void setModel(Group group) {
        this.group = group;
    }

    private void close() {
        Stage stage = (Stage) this.btnSave.getScene().getWindow();
        stage.close();
    }

    private Group getModelFromFields(Group group) {
        group.setCatequesis(catequesis.getSelectionModel().getSelectedItem());
        group.setName(name.getText());

        return group;
    }

    private void setFieldsFromModel() {
        this.catequesis.setValue(this.group.getCatequesis());
        this.name.setText(this.group.getName());
    }

    private void clearFields() {
        catequesis.setValue(null);
        name.clear();
    }
}
