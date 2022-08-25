package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.parish.attendancesb.view.FxmlView;
import org.parish.attendancesb.config.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SideBarController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private VBox attendancePane;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    void home(MouseEvent event) {
        mainPane.setCenter(attendancePane);
    }

    @FXML
    void page1(MouseEvent event) {
        loadPage(stageManager.getParent(FxmlView.CATEQUESIS_LIST));
    }

    @FXML
    void page2(MouseEvent event) {
        //loadPage(stageManager.getParent(FxmlView.GROUPS_LIST));
    }

    @FXML
    void page3(MouseEvent event) {
        loadPage(stageManager.getParent(FxmlView.RECEIVER_PEOPLE_LIST));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void loadPage(Parent page) {
        mainPane.setCenter(page);
    }
}
