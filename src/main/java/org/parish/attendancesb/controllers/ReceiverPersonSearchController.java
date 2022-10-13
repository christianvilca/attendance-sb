package org.parish.attendancesb.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ReceiverPersonSearchController implements Initializable {

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> lastName;

    @FXML
    private TableColumn<?, ?> firstName;

    @FXML
    private TextField search;

    @FXML
    private TableView<ReceiverPerson> table;

    private ReceiverPerson person;

    private ReceiverPersonController controller;

    private ReceiverPersonService service;

    public ReceiverPersonSearchController(ReceiverPersonController controller, ReceiverPersonService service) {
        //super(controller, service);
        this.controller = controller;
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                search.requestFocus();
            }
        });

        this.person = null;

        this.initializeObjects();
        this.refleshTable();
        this.setColumnFromModel();
        this.dblClickTable();
    }

    public void refleshTable() {
        String textSearch = this.search.getText().trim();

        List<ReceiverPerson> people;
        if (textSearch.isEmpty())
            people = this.service.findAll();
        else
            people = this.service.findByName(textSearch);

        this.table.setItems(FXCollections.observableArrayList(people));
        this.table.refresh();

        //this.total.setText(people.size() + "");
    }

    //    @Override
    public void initializeObjects() {
    }


    //@Override
    public void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    }

    @FXML
    void filter(KeyEvent event) {
        refleshTable();
        if (table.isFocused())
            return;

        if (event.getCode() == KeyCode.DOWN) {
            table.requestFocus();
            table.getSelectionModel().selectFirst();
        }
        if (event.getCode() == KeyCode.UP) {
            table.requestFocus();
            table.getSelectionModel().selectLast();
        }

    }

    @FXML
    void select(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.person = this.table.getSelectionModel().getSelectedItem();

            TableView tableView = (TableView) event.getSource();
            Stage stage = (Stage) tableView.getScene().getWindow();
            stage.close();
        }
    }

    public void dblClickTable() {
        this.table.setRowFactory(tv -> {
            TableRow<ReceiverPerson> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    this.person = row.getItem();

                    Stage stage = (Stage) search.getScene().getWindow();
                    stage.close();
                }
            });
            return row;
        });
    }

    public ReceiverPerson getModel() {
        return person;
    }
}
