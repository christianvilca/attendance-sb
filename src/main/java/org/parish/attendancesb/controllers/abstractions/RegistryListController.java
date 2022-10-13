package org.parish.attendancesb.controllers.abstractions;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import net.sf.jasperreports.engine.JRException;
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.report.Report;
import org.parish.attendancesb.services.interfaces.Service;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class RegistryListController<T> implements Initializable {

    @FXML
    private Label total;

    @FXML
    private TableView<T> table;

    @FXML
    private TextField search;

    protected FxmlView view;

    protected Jrxml report;

    @Autowired
    private Report reportService;

    @Lazy
    @Autowired
    private StageManager stageManager;

    protected RegistryController<T> controller;

    protected Service service;

    protected RegistryListController(RegistryController<T> controller, Service service) {
        this.controller = controller;
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> search.requestFocus());

        this.initializeObjects();
        this.refleshTable();
        this.setColumnFromModel();
        this.dblClickTable();
    }

    public void dblClickTable() {
        this.table.setRowFactory(tv -> {
            TableRow<T> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    row.getItem();
                    this.edit(null);
                }
            });
            return row;
        });
    }

    protected void setModal(FxmlView view) {
        this.view = view;
    }

    protected void setReport(Jrxml report) {
        this.report = report;
    }

    @FXML
    void export(ActionEvent event) {
        try {
            reportService.export(this.table.getItems(), report);
        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
        }
    }

    protected void showModal() {
        stageManager.sceneModal(view);

        if (controller.getModel() != null)
            this.refleshTable();
    }

    @FXML
    void newRegistry(ActionEvent event) {
        controller.setModel(null);
        showModal();
    }

    @FXML
    void filter(KeyEvent event) {
        refleshTable();
    }

    @FXML
    void edit(ActionEvent event) {
        T registry = this.table.getSelectionModel().getSelectedItem();

        if (registry == null) {
            Alert.error("Debe seleccionar un registro!");
            return;
        }

        controller.setModel(registry);
        showModal();
    }

    public void refleshTable() {
        String textSearch = this.search.getText().trim();

        List<T> registryList;
        if (textSearch.isEmpty())
            registryList = this.service.findAll();
        else
            registryList = this.service.findByName(textSearch);

        this.table.setItems(FXCollections.observableArrayList(registryList));
        this.table.refresh();

        this.total.setText(registryList.size() + "");
    }

    protected abstract void initializeObjects();

    protected abstract void setColumnFromModel();
}
