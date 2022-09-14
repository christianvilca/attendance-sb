package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.controllers.abstractions.RegistryListController;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.stereotype.Component;

@Component
public class GroupListController extends RegistryListController<Group> {

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> catequesis;

    @FXML
    private TableColumn<?, ?> name;

    private GroupController controller;

    private GroupService service;

    public GroupListController(GroupController controller, GroupService service) {
        super(controller, service);
        this.controller = controller;
        this.service = service;
    }

    @Override
    public void initializeObjects() {
        setModal(FxmlView.GROUP);
        setReport(Jrxml.GROUP_LIST);
    }

    @Override
    public void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory("id"));
        this.catequesis.setCellValueFactory(new PropertyValueFactory("catequesis"));
        this.name.setCellValueFactory(new PropertyValueFactory("name"));
    }

}
