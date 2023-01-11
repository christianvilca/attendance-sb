package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.controllers.utils.image.ImageFx;
import org.parish.attendancesb.controllers.utils.validation.ValidationComboBox;
import org.parish.attendancesb.controllers.utils.validation.ValidationList;
import org.parish.attendancesb.controllers.utils.validation.ValidationTextField;
import org.parish.attendancesb.controllers.utils.validation.ValidationType;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.SessionService;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ReceiverPersonController extends RegistryController<ReceiverPerson> {

    @FXML
    private Label lblTitle;

    @FXML
    private ComboBox<Group> group;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private ImageView imgPhoto;

    @FXML
    private Label lblPhoto;

    private Image image;

    private GroupService groupService;

    private SessionService sessionService;

    private ReceiverPersonService service;

    public ReceiverPersonController(
            ReceiverPersonService service,
            GroupService groupService,
            SessionService sessionService
    ) {
        super(service);
        this.service = service;
        this.groupService = groupService;
        this.sessionService = sessionService;
    }

    @Override
    public void initializeObjects() {
        group.getItems().addAll(groupService.findAll());
        lblTitle.setText(sessionService.getReceiverPersonTypeSingular());

        imgPhoto.setFitWidth(183);
        imgPhoto.setFitHeight(235);
        //imgPhoto.setPreserveRatio(true);
    }

    @Override
    public ReceiverPerson getModelFromFields() {
        ReceiverPerson person = getPerson();

        person.setGroup(group.getSelectionModel().getSelectedItem());
        person.setFirstName(txtFirstName.getText());
        person.setLastName(txtLastName.getText());
        person.setPhoto(ImageFx.encoder(imgPhoto.getImage()));

        return person;
    }


    private ReceiverPerson getPerson() {
        if (registry == null) {
            ReceiverPerson receiverPerson = new ReceiverPerson();
            receiverPerson.setCode(service.getCode());
            return receiverPerson;
        }

        return registry;
    }

    @Override
    public boolean isValid() {
        return ValidationList.isValid(
                new ValidationComboBox("Grupo", group),
                new ValidationTextField("Nombres", txtFirstName, ValidationType.TEXT),
                new ValidationTextField("Apellidos", txtLastName, ValidationType.TEXT)
        );
    }

    @Override
    public void setFieldsFromModel() {
        this.group.setValue(this.registry.getGroup());
        this.txtFirstName.setText(this.registry.getFirstName());
        this.txtLastName.setText(this.registry.getLastName());
        this.imgPhoto.setImage(ImageFx.decoder(this.registry.getPhoto()));
    }

    @Override
    public void clearFields() {
        group.getSelectionModel().selectFirst();
        txtFirstName.clear();
        txtLastName.clear();
        clearImageView();
    }

    @FXML
    void changeImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            image = new Image(file.toURI().toString(), 183, 235, true, true);
            imgPhoto.setImage(image);
        }
    }

    private void clearImageView() {
        // Limpiar el ImageView estableciendo la propiedad "image" en null
        imgPhoto.setImage(null);

        // Liberar la memoria utilizada por la imagen si no hay ningún otro objeto que esté haciendo referencia a ella
//        image.dispose();

        // Eliminar la referencia a la imagen estableciendo la variable en null
        image = null;
    }
}
