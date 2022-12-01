package org.parish.attendancesb.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.aspect.annotation.Function;
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.controllers.abstractions.RegistryListController;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.services.SessionService;
import org.parish.attendancesb.view.FxmlView;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;


@Controller
public class ReceiverPersonListController extends RegistryListController<ReceiverPerson> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @FXML
    private Label lblTitle;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> group;

    @FXML
    private TableColumn<?, ?> lastName;

    @FXML
    private TableColumn<?, ?> firstName;

    private AttendanceReportController attendanceReportController;

    private ReceiverPersonService service;

    private SessionService sessionService;

    @Lazy
    @Autowired
    private StageManager stageManager;

    public ReceiverPersonListController(
            ReceiverPersonController controller,
            ReceiverPersonService service,
            AttendanceReportController attendanceReportController,
            SessionService sessionService
    ) {
        super(controller, service);
        this.service = service;
        this.attendanceReportController = attendanceReportController;
        this.sessionService = sessionService;
    }

    @Function
    @Override
    public void initializeObjects() {
        setModal(FxmlView.RECEIVER_PEOPLE);
        setReport(Jrxml.RECEIVER_PERSON_LIST);
        this.addAllMenu();
        lblTitle.setText(sessionService.getReceiverPersonTypePlural());
    }

    private void addAllMenu() {
        addAllMenu(
                new SeparatorMenuItem(),
                getMenuAttendances()
        );
    }

    private MenuItem getMenuAttendances() {
        MenuItem menu = new MenuItem("Asistencias");
        menu.setOnAction((ActionEvent e) -> {
            logger.info(">> {}", getRow());
            attendanceReportController.setPerson(getRow());
            stageManager.sceneModal(FxmlView.ATTENDANCE_REPORT);
        });
        return menu;
    }

    @Override
    public void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.group.setCellValueFactory(new PropertyValueFactory<>("group"));
    }

}
