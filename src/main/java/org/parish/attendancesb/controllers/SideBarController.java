package org.parish.attendancesb.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.models.access.RoleType;
import org.parish.attendancesb.services.SessionService;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.services.interfaces.UserService;
import org.parish.attendancesb.view.FxmlView;
import org.parish.attendancesb.config.StageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.*;

@Controller
public class SideBarController implements Initializable {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @FXML
    private VBox boxManager;

    @FXML
    private VBox boxCoordinator;

    @FXML
    private VBox boxCatequista;

    @FXML
    private VBox sideBarMenu;

    @FXML
    private Button btnCatequesis;

    @FXML
    private Button btnCoordinators;

    @FXML
    private Button btnGroups;

    @FXML
    private Button btnCatequistas;

    @FXML
    private Button btnAttendance;

    @FXML
    private Button btnReceiverPerson;
    @FXML
    private MenuItem mnuCatequesisCambiar;
    @FXML
    private Button btnReport;

    @FXML
    private Label lblCatequesis;

    @FXML
    private BorderPane mainPane;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CatequesisSearchController controller;

    private Button beforeButton;

    @FXML
    void pageUser(MouseEvent event) {
        loadPage(stageManager.getParent(FxmlView.USER_LIST));
        setStyleButton((Button) event.getSource());
    }

    @FXML
    void pageCatequesis(MouseEvent event) {
        loadPage(stageManager.getParent(FxmlView.CATEQUESIS_LIST));
        setStyleButton((Button) event.getSource());
    }

    @FXML
    void pageCatequista(MouseEvent event) {
        loadPage(stageManager.getParent(FxmlView.CATEQUISTA_LIST));
        setStyleButton((Button) event.getSource());
    }

    @FXML
    void pageGroup(MouseEvent event) {
        if (sessionService.getCatequesis() == null) {
            this.changeCatequesis(null);
        }

        loadPage(stageManager.getParent(FxmlView.GROUP_LIST));
        setStyleButton((Button) event.getSource());
    }

    @FXML
    void pageAttendance(MouseEvent event) {
        loadPage(stageManager.getParent(FxmlView.ATTENDANCE));
        setStyleButton((Button) event.getSource());
    }


    @FXML
    void pageReceiverPerson(MouseEvent event) {
        loadPage(stageManager.getParent(FxmlView.RECEIVER_PEOPLE_LIST));
        setStyleButton((Button) event.getSource());
    }

    @FXML
    void logOut(ActionEvent event) {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    @FXML
    void changeCatequesis(ActionEvent event) {
        stageManager.sceneModal(FxmlView.CATEQUESIS_SEARCH);

        if (controller.getModel() != null) {
            sessionService.setCatequesis(controller.getModel());
            lblCatequesis.setText(controller.getModel().getName());
            loadPage(stageManager.getParent(FxmlView.ATTENDANCE));
            setStyleButton(btnAttendance);
        }
    }

    @FXML
    void changeLabelCatequesis(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                if (userService.authorize(RoleType.MANAGER.name())) {
                    changeCatequesis(null);
                    return;
                }
                Alert.information("No tienes permisos!");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stageManager.getStage().setResizable(true);
        stageManager.getStage().setMinWidth(816);
        stageManager.getStage().setMinHeight(646);

        loadPage(stageManager.getParent(FxmlView.ATTENDANCE));
        setStyleButton(btnAttendance);

        mnuCatequesisCambiar.setVisible(false);

        setBoxes();
    }

    private void setStyleButton(Button button) {
        String style = "button-pressed";

        button.getStyleClass().add(style);

        if (beforeButton != null)
            beforeButton.getStyleClass().remove(style);

        beforeButton = button;
    }

    private void setBoxes() {
        if (sideBarMenu.getChildren().contains(boxManager))
            sideBarMenu.getChildren().remove(boxManager);

        if (sideBarMenu.getChildren().contains(boxCoordinator))
            sideBarMenu.getChildren().remove(boxCoordinator);

        if (userService.authorize(RoleType.COORDINATOR.name())) {
            sideBarMenu.getChildren().add(1, boxCoordinator);
        }

        if (userService.authorize(RoleType.MANAGER.name())) {

            sideBarMenu.getChildren().add(1, boxManager);
            //sideBarMenu.getChildren().add(2, boxCoordinator);
            mnuCatequesisCambiar.setVisible(true);
        }

    }

    private void loadPage(Parent page) {
        mainPane.setCenter(page);
    }

}
