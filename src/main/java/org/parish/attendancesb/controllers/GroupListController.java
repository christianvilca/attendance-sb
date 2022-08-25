package org.parish.attendancesb.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
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
    private TableView<Group> table;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private GroupController controller;

    @Autowired
    private GroupService service;

    private ObservableList<Group> groups;

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
            this.groups = FXCollections.observableArrayList(this.service.findAll());
        else
            this.groups = FXCollections.observableArrayList(this.service.findAllByName(textSearch));

        this.table.setItems(this.groups);
        this.table.refresh();
    }
}
