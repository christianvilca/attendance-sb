package org.parish.attendancesb.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import net.sf.jasperreports.engine.JRException;
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.controllers.abstractions.RegistryListController;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.report.Report;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.services.interfaces.Service;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CatequesisListController extends RegistryListController<ReceiverPerson> {

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> day;

    @FXML
    private TableColumn<?, ?> timeStart;

    @FXML
    private TableColumn<?, ?> timeEnd;

    @FXML
    private TableColumn<?, ?> tolerance;

    private CatequesisController controller;

    private CatequesisService service;

    public CatequesisListController(CatequesisController controller, CatequesisService service) {
        super(controller, service);
        this.controller = controller;
        this.service = service;
    }

    @Override
    public void initializeObjects() {
        setModal(FxmlView.CATEQUESIS);
        setReport(Jrxml.CATEQUESIS_LIST);
    }

    @Override
    public void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory("id"));
        this.name.setCellValueFactory(new PropertyValueFactory("name"));
        this.day.setCellValueFactory(new PropertyValueFactory("day"));
        this.timeStart.setCellValueFactory(new PropertyValueFactory("timeStart"));
        this.timeEnd.setCellValueFactory(new PropertyValueFactory("timeEnd"));
        this.tolerance.setCellValueFactory(new PropertyValueFactory("tolerance"));
    }

}
