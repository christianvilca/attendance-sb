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
import org.parish.attendancesb.controllers.utils.alert.AlertFx;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.view.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.*;

@Controller
public class CatequesisCatequistaListController implements Initializable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @FXML
    private Label lblCatequesis;

    @FXML
    private TableView<Catequista> tableCatequistas;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> user;

    @FXML
    private TableColumn<?, ?> lastName;

    @FXML
    private TableColumn<?, ?> firstName;

    private Catequesis catequesis;

    private CatequistaSearchController controller;

    private Set<Catequista> catequistaList;

    private CatequesisService service;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblCatequesis.setText(catequesis.getName());

        catequistaList = new HashSet<>(catequesis.getCatequistas());
        this.refleshTable();
        this.setColumnFromModel();
    }

    public CatequesisCatequistaListController(CatequistaSearchController controller, CatequesisService service) {
        this.controller = controller;
        this.service = service;
    }

    @FXML
    void add(ActionEvent event) {
        if (this.tableCatequistas.getItems() != null)
            controller.removeAllRegistry(this.tableCatequistas.getItems());

        stageManager.sceneModal(FxmlView.CATEQUISTA_SEARCH);

        if (controller.getModel() != null) {
            catequistaList.add(controller.getModel());
            logger.info("-----> {}", catequistaList);
            this.refleshTable();
        }
    }

    private void refleshTable() {
        this.tableCatequistas.setItems(FXCollections.observableArrayList(catequistaList));
        this.tableCatequistas.refresh();
    }

    private void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.user.setCellValueFactory(new PropertyValueFactory<>("user"));
        this.lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    }

    @FXML
    void remove(ActionEvent event) {
        catequistaList.remove(this.tableCatequistas.getSelectionModel().getSelectedItem());
        this.refleshTable();
    }

    @FXML
    void save(ActionEvent event) {
        catequesis.setCatequistas(catequistaList);
        service.update(catequesis);
        AlertFx.information("Se ha guardado correctamente!");
        this.cancel(event);
    }

    @FXML
    void cancel(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    public Catequesis getCatequesis() {
        return catequesis;
    }

    public void setCatequesis(Catequesis catequesis) {
        this.catequesis = catequesis;
    }
}