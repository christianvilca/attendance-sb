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
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.report.Report;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class GroupListController implements Initializable {

    @FXML
    private TableColumn<?, ?> catequesis;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TextField search;

    @FXML
    private Label total;

    @FXML
    private TableView<Group> table;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private GroupController controller;

    @Autowired
    private GroupService service;

    @Autowired
    private Report report;

    private List<Group> groups;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                search.requestFocus();
            }
        });

        this.refleshTable();
        this.setColumnFromModel();
        this.dblClickTable();
    }

    private void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory("id"));
        this.catequesis.setCellValueFactory(new PropertyValueFactory("catequesis"));
        this.name.setCellValueFactory(new PropertyValueFactory("name"));
    }

    private void dblClickTable() {
        this.table.setRowFactory(tv -> {
            TableRow<Group> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Group rowData = row.getItem();
                    //System.out.println(rowData);
                    this.edit(null);
                }
            });
            return row;
        });
    }

    private void showModal() {
        stageManager.sceneModal(FxmlView.GROUP);

        if (controller.getModel() != null)
            this.refleshTable();
    }

    @FXML
    void export(ActionEvent event) {
        try {
            report.export(this.table.getItems(), Jrxml.GROUP_LIST);
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
        Group group = this.table.getSelectionModel().getSelectedItem();

        if (group == null) {
            Alert.error("Debe seleccionar un registro!");
            return;
        }

        controller.setModel(group);
        showModal();
    }

    @FXML
    void filter(KeyEvent event) {
        refleshTable();
    }

    private void refleshTable() {
        String textSearch = this.search.getText().trim();

        if (textSearch.isEmpty())
            this.groups = this.service.findAll();
        else
            this.groups = this.service.findAllByName(textSearch);

        this.table.setItems(FXCollections.observableArrayList(this.groups));
        this.table.refresh();

        this.total.setText(this.groups.size() + "");
    }
}
