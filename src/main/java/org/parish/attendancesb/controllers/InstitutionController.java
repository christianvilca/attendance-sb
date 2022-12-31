package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.controllers.utils.ValidationList;
import org.parish.attendancesb.controllers.utils.ValidationTextField;
import org.parish.attendancesb.controllers.utils.ValidationType;
import org.parish.attendancesb.services.carnet.CarnetFront;
import org.parish.attendancesb.controllers.utils.image.ImageFx;
import org.parish.attendancesb.models.Institution;
import org.parish.attendancesb.services.SessionService;
import org.parish.attendancesb.services.interfaces.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class InstitutionController extends RegistryController<Institution> {

    @FXML
    private Button btnSave;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lblPhoto;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtInstitution;

    @FXML
    private TextField txtName;

    private Image image;

    private SessionService sessionService;
    private InstitutionService service;

    @Autowired
    private CarnetFront grafics;

    public InstitutionController(
            InstitutionService service,
            SessionService sessionService
    ) {
        super(service);
        this.service = service;
        this.sessionService = sessionService;
    }

    @Override
    public void initializeObjects() {
        this.registry = service.getRegistry();
        imgLogo.setFitWidth(183);
        imgLogo.setFitHeight(235);
        //imgPhoto.setPreserveRatio(true);
    }

    @Override
    public Institution getModelFromFields() {
        Institution institution = getInstitution();

        institution.setInstitutionName(txtInstitution.getText());
        institution.setName(txtName.getText());
        institution.setLogo(ImageFx.encoder(imgLogo.getImage()));

        return institution;
    }


    private Institution getInstitution() {
        if (registry == null)
            return new Institution();

        return registry;
    }

    @Override
    public boolean isValid() {
        return ValidationList.isValid(
                new ValidationTextField("Institución", txtInstitution, ValidationType.TEXT),
                new ValidationTextField("Nombre de la Institución", txtName, ValidationType.TEXT)
        );
    }

    @Override
    public void setFieldsFromModel() {
        this.txtInstitution.setText(this.registry.getInstitutionName());
        this.txtName.setText(this.registry.getName());
        this.imgLogo.setImage(ImageFx.decoder(this.registry.getLogo()));
    }

    @Override
    public void clearFields() {
        txtInstitution.clear();
        txtName.clear();
        clearImageView();
    }

    @FXML
    void changeImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            image = new Image(file.toURI().toString(), 183, 235, true, true);
            imgLogo.setImage(image);
        }
    }

    private void clearImageView() {
        // Limpiar el ImageView estableciendo la propiedad "image" en null
        imgLogo.setImage(null);

        // Liberar la memoria utilizada por la imagen si no hay ningún otro objeto que esté haciendo referencia a ella
//        image.dispose();

        // Eliminar la referencia a la imagen estableciendo la variable en null
        image = null;
    }
}
