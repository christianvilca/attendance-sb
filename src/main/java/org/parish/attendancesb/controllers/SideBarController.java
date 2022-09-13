package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.parish.attendancesb.services.attendance.AttendanceType;
import org.parish.attendancesb.view.FxmlView;
import org.parish.attendancesb.config.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SideBarController implements Initializable {

    @FXML
    private Button btnAttendance;

    @FXML
    private Button btnCatequesis;

    @FXML
    private Button btnGroups;

    @FXML
    private Button btnReceiverPerson;

    @FXML
    private BorderPane mainPane;

    @Lazy
    @Autowired
    private StageManager stageManager;

    Set<Button> buttons;

    @FXML
    void home(MouseEvent event) {
        loadPage(stageManager.getParent(FxmlView.ATTENDANCE));
        setStyle((Button) event.getSource());
    }

    @FXML
    void page1(MouseEvent event) {
        loadPage(stageManager.getParent(FxmlView.CATEQUESIS_LIST));
        setStyle((Button) event.getSource());
    }

    @FXML
    void page2(MouseEvent event) {
        loadPage(stageManager.getParent(FxmlView.GROUP_LIST));
        setStyle((Button) event.getSource());
    }

    @FXML
    void page3(MouseEvent event) {
        loadPage(stageManager.getParent(FxmlView.RECEIVER_PEOPLE_LIST));
        setStyle((Button) event.getSource());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new HashSet<>();
        buttons.add(btnAttendance);
        buttons.add(btnCatequesis);
        buttons.add(btnGroups);
        buttons.add(btnReceiverPerson);

        loadPage(stageManager.getParent(FxmlView.ATTENDANCE));
        setStyle(btnAttendance);
    }

    private void setStyle(Button button) {
        button.getStyleClass().add("button-pressed");
        buttons.stream()
                .filter(b -> b != button)
                .forEach(b -> b.getStyleClass().remove("button-pressed"));
    }

    private void loadPage(Parent page) {
        mainPane.setCenter(page);
    }

}
