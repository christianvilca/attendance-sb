package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.controllers.abstractions.RegistryListController;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.view.FxmlView;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.stereotype.Component;

@Component
public class ReceiverPersonListController extends RegistryListController<ReceiverPerson> {

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> group;

    @FXML
    private TableColumn<?, ?> lastName;

    @FXML
    private TableColumn<?, ?> firstName;

    private ReceiverPersonController controller;

    private ReceiverPersonService service;

    public ReceiverPersonListController(ReceiverPersonController controller, ReceiverPersonService service) {
        super(controller, service);
        this.controller = controller;
        this.service = service;
    }

    @Override
    public void initializeObjects() {
        setModal(FxmlView.RECEIVER_PEOPLE);
        setReport(Jrxml.RECEIVER_PERSON_LIST);
    }

    @Override
    public void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory("id"));
        this.lastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        this.firstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        this.group.setCellValueFactory(new PropertyValueFactory("group"));
    }

}
