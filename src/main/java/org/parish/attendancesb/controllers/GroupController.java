package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.controllers.utils.validation.ValidationList;
import org.parish.attendancesb.controllers.utils.validation.ValidationTextField;
import org.parish.attendancesb.controllers.utils.validation.ValidationType;
import org.parish.attendancesb.controllers.utils.color.ColorFx;
import org.parish.attendancesb.controllers.utils.image.ImageFx;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.services.SessionService;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class GroupController extends RegistryController<Group> {

    @FXML
    private ColorPicker color;

    @FXML
    private TextField colorFake;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lblPhoto;

    @FXML
    private TextField name;
    private Image image;
    private final SessionService sessionService;

    public GroupController(GroupService service, SessionService sessionService) {
        super(service);
        this.sessionService = sessionService;
    }

    @Override
    public void initializeObjects() {
        imgLogo.setFitWidth(183);
        imgLogo.setFitHeight(235);
    }

    @Override
    public Group getModelFromFields() {
        Group group = getGroup();

        group.setCatequesis(sessionService.getCatequesis());
        group.setName(name.getText());
        group.setColor(ColorFx.rgba2Hex(color.getValue()));
        group.setLogo(ImageFx.encoder(imgLogo.getImage()));

        return group;
    }

    private Group getGroup() {
        if (registry == null)
            return new Group();

        return registry;
    }

    @Override
    protected boolean isValid() {
        return ValidationList.isValid(
                new ValidationTextField("Nombre", name, ValidationType.TEXT)
        );
    }

    @Override
    public void setFieldsFromModel() {
        this.name.setText(this.registry.getName());
        this.color.setValue(ColorFx.hex2Rgb(this.registry.getColor()));
        this.imgLogo.setImage(ImageFx.decoder(this.registry.getLogo()));
    }

    @Override
    public void clearFields() {
        name.clear();
        color.setValue(Color.BLUE);
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
