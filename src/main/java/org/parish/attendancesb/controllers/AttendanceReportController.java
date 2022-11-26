package org.parish.attendancesb.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.models.report.AttendanceDetailReport;
import org.parish.attendancesb.services.attendance.Resume;
import org.parish.attendancesb.services.interfaces.AttendanceService;
import org.parish.attendancesb.services.report.AttendanceReportService;
import org.parish.attendancesb.view.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AttendanceReportController implements Initializable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    private ReceiverPerson receiverPerson;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private AttendanceService service;

    @Autowired
    private AttendanceReportService attendanceReportService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setColumnFromModel();
        this.setReport();
    }

    public void setColumnFromModel() {
        this.number.setCellValueFactory(new PropertyValueFactory<>("number"));
        this.month.setCellValueFactory(new PropertyValueFactory<>("month"));
        this.day.setCellValueFactory(new PropertyValueFactory<>("day"));
        this.timeStart.setCellValueFactory(new PropertyValueFactory<>("timeStart"));
        this.timeEnd.setCellValueFactory(new PropertyValueFactory<>("timeEnd"));
    }

    public void setPerson(ReceiverPerson person) {
        this.receiverPerson = person;
        logger.info("{}", person.getFirstName());
        logger.info("{}", person.getCode());
    }

    private void setReport(){

        this.person.setText(receiverPerson.toString());
        attendanceReportService.setAttendanceReport(receiverPerson);

        Resume resume = service.resume(receiverPerson);
        attendances.setText(resume.getPresents() + "");
        lates.setText(resume.getLates() + "");
        absents.setText(resume.getAbsents() + "");
        total.setText(resume.getTotal() + "");

        this.table.setItems(FXCollections.observableArrayList(attendanceReportService.getDetails()));
        this.table.refresh();
    }

    @FXML
    void exit(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    @FXML
    void export(ActionEvent event) {
        attendanceReportService.exportReport(receiverPerson);
    }
}
