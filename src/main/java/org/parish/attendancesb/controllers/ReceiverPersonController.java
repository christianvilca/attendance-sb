package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.controllers.utils.*;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.SessionService;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.stereotype.Component;

@Component
public class ReceiverPersonController extends RegistryController<ReceiverPerson> {

    @FXML
    private Label lblTitle;

    @FXML
    private ComboBox<Group> group;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    private GroupService groupService;

    private SessionService sessionService;

    public ReceiverPersonController(
            ReceiverPersonService service,
            GroupService groupService,
            SessionService sessionService
    ) {
        super(service);
        this.groupService = groupService;
        this.sessionService = sessionService;
    }

    @Override
    public void initializeObjects() {
        group.getItems().addAll(groupService.findAll());
        lblTitle.setText(sessionService.getReceiverPersonTypeSingular());
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
    public boolean isValid() {
        return ValidationList.isValid(
                new ValidationComboBox("Grupo", group),
                new ValidationTextField("Nombres", txtFirstName, ValidationType.TEXT),
                new ValidationTextField("Apellidos", txtLastName, ValidationType.TEXT)
        );
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
