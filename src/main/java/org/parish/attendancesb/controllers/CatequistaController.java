package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.controllers.utils.ValidationList;
import org.parish.attendancesb.controllers.utils.ValidationTextField;
import org.parish.attendancesb.controllers.utils.ValidationType;
import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.services.interfaces.*;
import org.springframework.stereotype.Component;

@Component
public class CatequistaController extends RegistryController<Catequista> {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private ComboBox<User> user;

    private CatequistaService service;

    public CatequistaController(CatequistaService service) {
        super(service);
        this.service = service;
    }

    @Override
    public void initializeObjects() {
        fillUsers();
    }

    private void fillUsers() {
        user.setDisable(false);
        user.setValue(null);
        user.getItems().clear();

        user.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(user.getPromptText());
                } else {
                    setText(item.toString());
                }
            }
        });

        if (this.registry != null) {
            service.addUser(this.registry.getUser());
        }
        user.getItems().addAll(
                service.getUserList()
        );
    }

    @Override
    public Catequista getModelFromFields() {
        Catequista catequista = getCatequista();

        catequista.setLastName(lastName.getText());
        catequista.setFirstName(firstName.getText());
        catequista.setUser(user.getSelectionModel().getSelectedItem());

        return catequista;
    }

    private Catequista getCatequista() {
        if (registry == null)
            return new Catequista();

        return registry;
    }

    @Override
    protected boolean isValid() {
        return ValidationList.isValid(
                new ValidationTextField("Apellidos", lastName, ValidationType.TEXT),
                new ValidationTextField("Nombres", firstName, ValidationType.TEXT)
        );
    }

    @Override
    public void setFieldsFromModel() {
        this.lastName.setText(this.registry.getLastName());
        this.firstName.setText(this.registry.getFirstName());
        this.user.setValue(this.registry.getUser());
    }

    @Override
    public void clearFields() {
        firstName.clear();
        lastName.clear();
    }
}
