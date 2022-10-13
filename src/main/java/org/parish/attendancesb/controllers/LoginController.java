package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.services.interfaces.UserService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable {
    @FXML
    private Button btnFB;

    @FXML
    private Label btnForgot;

    @FXML
    private Button btnSignin;

    @FXML
    private Button btnSignup;

    @FXML
    private Label lblErrors;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private UserService userService;

    @FXML
    void handleButtonAction(MouseEvent event) {
        if (userService.authenticate(txtUsername.getText(), txtPassword.getText())) {
            stageManager.switchScene(FxmlView.MAIN);
            return;
        }

        Alert.error("Usuario o password incorrecto!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stageManager.getStage().setResizable(false);
        stageManager.getStage().setMinWidth(854);
        stageManager.getStage().setMinHeight(503);
    }
}
