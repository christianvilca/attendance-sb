package org.parish.attendancesb.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import net.sf.jasperreports.engine.JRException;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.report.Report;
import org.parish.attendancesb.services.report.AttendanceReportService;
import org.parish.attendancesb.view.FxmlView;
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
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
    private TableColumn<?, ?> group;

    @FXML
    private TableView<ReceiverPerson> table;

    @FXML
    private TextField txtSearch;

    @FXML
    private Label total;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private ReceiverPersonController controller;

    @Autowired
    private ReceiverPersonService service;

    @Autowired
    private Report report;

    private List<ReceiverPerson> people;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtSearch.requestFocus();
            }
        });

        this.refleshTable();
        this.setColumnFromModel();
        this.dblClickTable();
    }

    private void setColumnFromModel() {
        this.colId.setCellValueFactory(new PropertyValueFactory("id"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("lastName"));
        this.colNombres.setCellValueFactory(new PropertyValueFactory("firstName"));
        this.group.setCellValueFactory(new PropertyValueFactory("group"));
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

    private void showModal() {
        stageManager.sceneModal(FxmlView.RECEIVER_PEOPLE);

        if (controller.getModel() != null)
            this.refleshTable();
    }

    @FXML
    void export(ActionEvent event) {
        try {
            report.export(this.table.getItems(), Jrxml.RECEIVER_PERSON_LIST);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void newRegistry(ActionEvent event) {
        controller.setModel(null);
        showModal();
    }

    @FXML
    void edit(ActionEvent event) {
        ReceiverPerson person = this.table.getSelectionModel().getSelectedItem();

        if (person == null) {
            Alert.error("Debe seleccionar un registro!");
            return;
        }

        controller.setModel(person);
        showModal();
    }

    @FXML
    void filter(KeyEvent event) {
        refleshTable();
    }

    private void refleshTable() {
        String textSearch = this.txtSearch.getText().trim();

        if (textSearch.isEmpty())
            this.people = this.service.findAll();
        else
            this.people = this.service.findByName(textSearch);

        this.table.setItems(FXCollections.observableArrayList(this.people));
        this.table.refresh();

        this.total.setText(this.people.size() + "");
    }
}
