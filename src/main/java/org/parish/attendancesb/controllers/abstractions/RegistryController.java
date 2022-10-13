package org.parish.attendancesb.controllers.abstractions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.services.interfaces.Service;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class RegistryController<T> implements Initializable {

    protected T registry;

    protected Service service;

    protected RegistryController(Service service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeObjects();

        if (registry == null) {
            clearFields();
            return;
        }

        setFieldsFromModel();
    }

    @FXML
    void save(ActionEvent event) {
        T newRegistry = this.getModelFromFields();

        if (this.service.contains(newRegistry)) {
            Alert.error("El registro ya existe!");
            return;
        }

        saveRegistry(newRegistry);

        Alert.information("Se ha guardado correctamente!");

        this.cancel(event);
    }

    private void saveRegistry(T registry){
        if (this.registry == null) {
            this.registry = registry;
            this.service.save(registry);
            return;
        }

        this.service.update(registry);
    }

    @FXML
    void cancel(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    public T getModel() {
        return this.registry;
    }

    public void setModel(T t) {
        registry = t;
    }

    protected abstract void initializeObjects();

    protected abstract T getModelFromFields();

    protected abstract void setFieldsFromModel();

    protected abstract void clearFields();
}
