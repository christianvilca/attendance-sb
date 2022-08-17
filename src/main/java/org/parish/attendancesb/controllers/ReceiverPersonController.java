package org.parish.attendancesb.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.parish.attendancesb.controllers.window.Alert;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ReceiverPersonController implements Initializable {

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    private ReceiverPerson person;

    private ObservableList<ReceiverPerson> people;

    @Autowired
    private ReceiverPersonService service;

//    public ReceiverPersonController(ReceiverPersonService service) {
//        this.service = service;
//    }

    @FXML
    void save(ActionEvent event) {
        this.people = FXCollections.observableArrayList(this.service.findAll());

        ReceiverPerson person = this.getWindow();

        if (!this.people.contains(person)) {

            if (this.person != null) {
                this.person = person; //.clone();
                this.service.update(this.person);
                Alert.information("Se ha modificado correctamente");
            } else {
                this.person = person;
                this.service.save(this.person);
                Alert.information("Se ha guardado correctamente");
            }
            this.close();

            //this.partidas.add(newPartida); // es mejor hacerlo en la principal
            //this.tblPersonas.setItems(partidas); // actualizar tabla
        } else {
            Alert.error("La partida ya existe");
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        this.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtLastName.requestFocus();
            }
        });
    }

    public ReceiverPerson getRegistry() {
        return this.person;
    }

    public void setRegistry(ReceiverPerson person) {
        this.person = person;
    }

    private void close() {
        this.person = null;
        Stage stage = (Stage) this.btnSave.getScene().getWindow();
        stage.close();
    }

    private ReceiverPerson getWindow() {
        ReceiverPerson person = new ReceiverPerson();
        person.setFirstName(txtFirstName.getText());
        person.setLastName(txtLastName.getText());

        return person;
    }

    private void setWindow() {
        this.txtFirstName.setText(this.person.getFirstName());
        this.txtLastName.setText(this.person.getLastName());
    }
}
