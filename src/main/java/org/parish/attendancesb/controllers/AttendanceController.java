package org.parish.attendancesb.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.services.interfaces.AttendanceService;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.parish.attendancesb.services.report.AttendanceReportService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class AttendanceController implements Initializable {

    @FXML
    private Text clock;

    @FXML
    private TextField code;

    @FXML
    private Text firstName;

    @FXML
    private Text lastName;

    @FXML
    private Text checkIn;

    private String codePerson;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private AttendanceService service;

    @Autowired
    private ReceiverPersonService receiverPersonService;

    @Autowired
    private AttendanceReportController attendanceReportController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> code.requestFocus());
        bindToTime();
    }

    @FXML
    void enter(KeyEvent event) {

        if (!event.getCode().equals(KeyCode.ENTER))
            return;

        codePerson = code.getText();
        code.setText("");

        if (codePerson == null || codePerson.trim().equals("")) {
            Alert.information("Debe ingresar un codigo!");
            return;
        }

        if (!receiverPersonService.existsById(Integer.parseInt(codePerson))) {
            Alert.error("El codigo: " + codePerson + " no existe!");
            return;
        }

        Optional<Attendance> attendance = service.register(codePerson);
        firstName.setText(attendance.get().getReceiverPerson().getFirstName());
        lastName.setText(attendance.get().getReceiverPerson().getLastName());
        checkIn.setText(attendance.get().getDateTime().getTime().toAMPM());
    }

    @FXML
    void report(ActionEvent event) {
        if (codePerson == null || codePerson.trim().equals("")) {
            Alert.information("Debe ingresar un codigo!");
            code.requestFocus();
            return;
        }

        showModal();
        code.requestFocus();
    }

    private void bindToTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss a");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setText(LocalTime.now().format(dtf));
            }

            private void setText(String format) {
                clock.setText(format);
            }
        }),
                new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void showModal() {
        attendanceReportController.setPerson(receiverPersonService.getById(Integer.valueOf(codePerson)));
        stageManager.sceneModal(FxmlView.ATTENDANCE_REPORT);

//        if (controller.getModel() != null)
//            this.refleshTable();
    }
}
