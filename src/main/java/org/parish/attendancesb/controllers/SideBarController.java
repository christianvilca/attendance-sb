package org.parish.attendancesb.controllers;

import javafx.application.Platform;
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
import org.parish.attendancesb.controllers.utils.alert.AlertFx;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.access.RoleType;
import org.parish.attendancesb.services.MainService;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    private Label lblUser;

    @FXML
    private BorderPane mainPane;

    private Button beforeButton;

    @Lazy
    @Autowired
    private StageManager stageManager;

    private MainService mainService;

    private final CatequesisSearchController catequesisSearchController;

    public SideBarController(
            MainService mainService,
            CatequesisSearchController catequesisSearchController
    ) {
        this.mainService = mainService;
        this.catequesisSearchController = catequesisSearchController;
    }

    @FXML
    void pageUser(MouseEvent event) {
        mainService.setCatequesis(null);
        loadPage(stageManager.getParent(FxmlView.USER_LIST));
        setStyleButton((Button) event.getSource());
    }

    @FXML
    void pageCatequesis(MouseEvent event) {
        mainService.setCatequesis(null);
        loadPage(stageManager.getParent(FxmlView.CATEQUESIS_LIST));
        setStyleButton((Button) event.getSource());
    }

    @FXML
    void pageCatequista(MouseEvent event) {
        mainService.setCatequesis(null);
        loadPage(stageManager.getParent(FxmlView.CATEQUISTA_LIST));
        setStyleButton((Button) event.getSource());
    }

    @FXML
    void pageGroup(MouseEvent event) {
        if (mainService.getCatequesis() == null) {
            setCatequesis();
        }

        loadPage(stageManager.getParent(FxmlView.GROUP_LIST));
        setStyleButton((Button) event.getSource());
    }

    @FXML
    void pageAttendance(MouseEvent event) {
        if (mainService.getCatequesis() == null) {
            setCatequesis();
        }
        loadPage(stageManager.getParent(FxmlView.ATTENDANCE));
        setStyleButton((Button) event.getSource());
    }


    @FXML
    void pageReceiverPerson(MouseEvent event) {
        if (mainService.getCatequesis() == null) {
            setCatequesis();
        }
        loadPage(stageManager.getParent(FxmlView.RECEIVER_PERSON_LIST));
        setStyleButton((Button) event.getSource());
    }

    @FXML
    void logOut(ActionEvent event) {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    @FXML
    void changeCatequesis(ActionEvent event) {
        setCatequesis();
    }

    @FXML
    void pageInstitution(ActionEvent event) {
        stageManager.sceneModal(FxmlView.INSTITUTION);
    }

    private void setCatequesis() {
        mainService.setAutorizeAllCatequesis(mainService.authorize(RoleType.MANAGER.name()));

        if (mainService.hasOne()) {
            setData();
            return;
        }

        if (mainService.hasMany() || mainService.authorize(RoleType.MANAGER.name())) {
            catequesisSearchController.setService(mainService.getCatequesisSearchService());
            stageManager.sceneModal(FxmlView.CATEQUESIS_SEARCH);
            if (catequesisSearchController.getModel() != null) {
                mainService.setCatequesis(catequesisSearchController.getModel());
            }
            setData();
        }
    }

    private void setData() {
        lblCatequesis.setText(mainService.getCatequesis().getName());
        lblUser.setText(mainService.getUser().toString());
        btnReceiverPerson.setText(mainService.getReceiverPersonTypePlural());
        loadPage(stageManager.getParent(FxmlView.ATTENDANCE));
        setStyleButton(btnAttendance);
    }

    @FXML
    void changeLabelCatequesis(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                if (mainService.hasMany() || mainService.authorize(RoleType.MANAGER.name())) {
                    //changeCatequesis(null);
                    setCatequesis();
                    return;
                }
                AlertFx.information("No tienes permisos!");
            }
        }

    }

    @FXML
    void exit(ActionEvent event) {
        if (AlertFx.yesno("¿Está seguro de salir de la aplicación?")) {
            Platform.exit();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stageManager.getStage().setResizable(true);
        stageManager.getStage().setMinWidth(816);
        stageManager.getStage().setMinHeight(646);

        setData();

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

        if (mainService.authorize(RoleType.COORDINATOR.name()))
            sideBarMenu.getChildren().add(1, boxCoordinator);

        if (mainService.authorize(RoleType.MANAGER.name()))
            sideBarMenu.getChildren().add(1, boxManager);

        if (mainService.hasMany() || mainService.authorize(RoleType.MANAGER.name()))
            mnuCatequesisCambiar.setVisible(true);
    }

    private void loadPage(Parent page) {
        mainPane.setCenter(page);
    }

}
