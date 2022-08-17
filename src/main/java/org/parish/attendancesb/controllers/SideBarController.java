package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.parish.attendancesb.config.FxmlView;
import org.parish.attendancesb.config.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SideBarController implements Initializable {

    @FXML
    private BorderPane mainPane;
    
    @FXML
    private VBox attendancePane;

    @FXML
    void home(MouseEvent event) {
        mainPane.setCenter(attendancePane);
    }

    @FXML
    void page1(MouseEvent event) {
        loadPage("ReceiverPersonList");
    }

    @FXML
    void page2(MouseEvent event) {
        loadPage("ReceiverPerson");
    }

    @FXML
    void page3(MouseEvent event) {
        loadPage("page3");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/" + page + ".fxml"));

        } catch (IOException e) {
            Logger.getLogger(SideBarController.class.getName()).log(Level.SEVERE, null, e);
        }

        mainPane.setCenter(root);
    }
}
