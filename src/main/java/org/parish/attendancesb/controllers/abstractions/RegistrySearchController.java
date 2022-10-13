package org.parish.attendancesb.controllers.abstractions;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.parish.attendancesb.services.interfaces.Service;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class RegistrySearchController<T> implements Initializable {

    protected T registry;
    @FXML
    private TableView<T> table;

    @FXML
    private TextField search;

    protected Service service;

    protected RegistrySearchController(Service service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> search.requestFocus());

        this.registry = null;

        this.refleshTable();
        this.setColumnFromModel();
        this.dblClickTable();
    }

    public void dblClickTable() {
        this.table.setRowFactory(tv -> {
            TableRow<T> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    this.registry = row.getItem();

                    Stage stage = (Stage) search.getScene().getWindow();
                    stage.close();
                }
            });
            return row;
        });
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

    public void refleshTable() {
        String textSearch = this.search.getText().trim();

        List<T> registryList;
        if (textSearch.isEmpty())
            registryList = this.service.findAll();
        else
            registryList = this.service.findByName(textSearch);

        this.table.setItems(FXCollections.observableArrayList(registryList));
        this.table.refresh();
    }

    @FXML
    void select(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.registry = this.table.getSelectionModel().getSelectedItem();

            TableView<T> tableView = (TableView) event.getSource();
            Stage stage = (Stage) tableView.getScene().getWindow();
            stage.close();
        }
    }

    public T getModel() {
        return this.registry;
    }

    protected abstract void setColumnFromModel();
}
