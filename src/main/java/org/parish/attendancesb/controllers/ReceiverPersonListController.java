package org.parish.attendancesb.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
//import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.parish.attendancesb.controllers.window.Alert;
import org.parish.attendancesb.controllers.window.ParentWindow;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.repositories.ReceiverPersonRepository;
import org.parish.attendancesb.services.ReceiverPersonServiceImpl;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ReceiverPersonListController implements Initializable {

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colApellidos;

    @FXML
    private TableColumn<?, ?> colNombres;

    @FXML
    private TableView<ReceiverPerson> table;

    @FXML
    private TextField txtSearch;

    private ObservableList<ReceiverPerson> people;

    ReceiverPersonService service;

    public ReceiverPersonListController(ReceiverPersonService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtSearch.requestFocus();
            }
        });

        //this.service = new ReceiverPersonServiceImpl();

        this.refleshTable();

        this.setModelToColumn();

        this.dblClickTable();

        //txtSearch.requestFocus();
    }

    public ReceiverPersonController getController(String rootFxml) {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(rootFxml));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            Alert.error(e.getMessage());
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

        return loader.getController();
    }

    private void setModelToColumn() {
        // Se asocia con el modelo
        this.colId.setCellValueFactory(new PropertyValueFactory("id"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("firstName"));
        this.colNombres.setCellValueFactory(new PropertyValueFactory("lastName"));
    }

    private void refleshTable() {
        //this.people = FXCollections.observableArrayList(this.service.findAll());
        //this.filterPartidas = FXCollections.observableArrayList();

        this.table.setItems(this.people);
        this.table.refresh();
    }

    private void dblClickTable() {
        this.table.setRowFactory(tv -> {
            TableRow<ReceiverPerson> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ReceiverPerson rowData = row.getItem();
                    //System.out.println(rowData);
                    this.edit(null);
                }
            });
            return row;
        });
    }

    final String FXML = "/view/ReceiverPerson.fxml";

    @FXML
    void newRegistry(ActionEvent event) {
        ReceiverPersonController controller = this.getController(FXML);

        if (controller.getRegistry() != null)
            this.refleshTable();
    }

    @FXML
    void edit(ActionEvent event) {
        ReceiverPerson person = this.table.getSelectionModel().getSelectedItem();

        if (person == null) {
            Alert.error("Se debe seleccionar una partida");
        } else {
            ReceiverPersonController controller = this.getController(FXML);
            controller.setRegistry(person);

            if (controller.getRegistry() != null) {
                this.table.refresh();
            }
        }
    }

    @FXML
    void filter(KeyEvent event) {
        String textSearch = this.txtSearch.getText().trim();

        if (textSearch.isEmpty()) {
            this.refleshTable();
        } else {
            //this.people = FXCollections.observableArrayList(this.service.list());
            ObservableList<ReceiverPerson> people = FXCollections.observableArrayList(this.service.findByName(textSearch));

            this.table.setItems(people);
        }
    }

}
