package org.parish.attendancesb.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.models.report.AttendanceDetailReport;
import org.parish.attendancesb.services.attendance.Resume;
import org.parish.attendancesb.services.interfaces.AttendanceService;
import org.parish.attendancesb.services.report.AttendanceReportService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CatequesisReportController implements Initializable {

    @FXML
    private Label person;

    @FXML
    private Label attendances;

    @FXML
    private Label lates;

    @FXML
    private Label absents;

    @FXML
    private Label total;

    @FXML
    private TableColumn<?, ?> number;

    @FXML
    private TableColumn<?, ?> month;

    @FXML
    private TableColumn<?, ?> day;

    @FXML
    private TableColumn<?, ?> timeStart;

    @FXML
    private TableColumn<?, ?> timeEnd;

    @FXML
    private TableView<AttendanceDetailReport> table;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private ReceiverPersonSearchController controller;

    @Autowired
    private AttendanceService service;

    @Autowired
    private AttendanceReportService attendanceReportService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setColumnFromModel();
    }

    public void setColumnFromModel() {
        this.number.setCellValueFactory(new PropertyValueFactory("number"));
        this.month.setCellValueFactory(new PropertyValueFactory("month"));
        this.day.setCellValueFactory(new PropertyValueFactory("day"));
        this.timeStart.setCellValueFactory(new PropertyValueFactory("timeStart"));
        this.timeEnd.setCellValueFactory(new PropertyValueFactory("timeEnd"));
    }

    @FXML
    void btnSearch(ActionEvent event) {
        stageManager.sceneModal(FxmlView.RECEIVER_PEOPLE_SEARCH);

        if (controller.getModel() != null)
            this.setPerson(controller.getModel());
    }

    private void setPerson(ReceiverPerson person) {
        this.person.setText(person.toString());
        attendanceReportService.setAttendanceReport(person);

        Resume resume = service.resume(person);
        attendances.setText(resume.getPresents() + "");
        lates.setText(resume.getLates() + "");
        absents.setText(resume.getAbsents() + "");
        total.setText(resume.getTotal() + "");

        this.table.setItems(FXCollections.observableArrayList(attendanceReportService.getDetails()));
        this.table.refresh();
    }

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void export(ActionEvent event) {
        attendanceReportService.exportReport(controller.getModel());
    }
}
