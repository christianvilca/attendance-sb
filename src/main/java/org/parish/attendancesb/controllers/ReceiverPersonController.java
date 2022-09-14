package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.stereotype.Component;

@Component
public class ReceiverPersonController extends RegistryController<ReceiverPerson> {

    @FXML
    private ComboBox<Group> group;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    private GroupService groupService;

    private ReceiverPersonService service;

    public ReceiverPersonController(ReceiverPersonService service, GroupService groupService) {
        super(service);
        this.service = service;
        this.groupService = groupService;
    }

    @Override
    public void initializeObjects() {
        group.getItems().addAll(groupService.findAll());
    }

    @Override
    public ReceiverPerson getModelFromFields() {
        ReceiverPerson person = getPerson();

        person.setCode("00");
        person.setGroup(group.getSelectionModel().getSelectedItem());
        person.setFirstName(txtFirstName.getText());
        person.setLastName(txtLastName.getText());

        return person;
    }

    private ReceiverPerson getPerson() {
        if (registry == null)
            return new ReceiverPerson();

        return registry;
    }

    @Override
    public void setFieldsFromModel() {
        this.group.setValue(this.registry.getGroup());
        this.txtFirstName.setText(this.registry.getFirstName());
        this.txtLastName.setText(this.registry.getLastName());
    }

    @Override
    public void clearFields() {
        group.getSelectionModel().selectFirst();
        txtFirstName.clear();
        txtLastName.clear();
    }
}
