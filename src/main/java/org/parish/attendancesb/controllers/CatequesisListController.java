package org.parish.attendancesb.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import net.sf.jasperreports.engine.JRException;
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.report.Report;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CatequesisListController implements Initializable {

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> day;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TextField search;

    @FXML
    private Label total;

    @FXML
    private TableView<Catequesis> table;

    @FXML
    private TableColumn<?, ?> timeEnd;

    @FXML
    private TableColumn<?, ?> timeStart;

    @FXML
    private TableColumn<?, ?> tolerance;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private CatequesisController controller;

    @Autowired
    private CatequesisService service;

    @Autowired
    private Report report;

    private List<Catequesis> catequeses;

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
        this.name.setCellValueFactory(new PropertyValueFactory("name"));
        this.day.setCellValueFactory(new PropertyValueFactory("day"));
        this.timeStart.setCellValueFactory(new PropertyValueFactory("timeStart"));
        this.timeEnd.setCellValueFactory(new PropertyValueFactory("timeEnd"));
        this.tolerance.setCellValueFactory(new PropertyValueFactory("tolerance"));
    }

    private void dblClickTable() {
        this.table.setRowFactory(tv -> {
            TableRow<Catequesis> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Catequesis rowData = row.getItem();
                    //System.out.println(rowData);
                    this.edit(null);
                }
            });
            return row;
        });
    }

    private void showModal() {
        stageManager.sceneModal(FxmlView.CATEQUESIS);

        if (controller.getModel() != null)
            this.refleshTable();
    }

    @FXML
    void export(ActionEvent event) {
        try {
            report.export(this.table.getItems(), Jrxml.CATEQUESIS_LIST);
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
        Catequesis catequesis = this.table.getSelectionModel().getSelectedItem();

        if (catequesis == null) {
            Alert.error("Debe seleccionar un registro!");
            return;
        }

        controller.setModel(catequesis);
        showModal();
    }

    @FXML
    void filter(KeyEvent event) {
        refleshTable();
    }

    private void refleshTable() {
        String textSearch = this.search.getText().trim();

        if (textSearch.isEmpty())
            this.catequeses = this.service.findAll();
        else
            this.catequeses = this.service.findByName(textSearch);

        this.table.setItems(FXCollections.observableArrayList(this.catequeses));
        this.table.refresh();

        this.total.setText(this.catequeses.size() + "");
    }
}
