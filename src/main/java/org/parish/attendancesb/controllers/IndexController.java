package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    @Autowired
    @Qualifier("lblTitle")
    private String title;

    @FXML
    private Label lblTitle;

    // Cuando se cargan todos los componentes
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTitle.setText(title);
    }
}
