package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.services.interfaces.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserController extends RegistryController<User> {

    @FXML
    private TextField password;

    @FXML
    private TextField passwordConfirm;

    @FXML
    private TextField username;

    public UserController(UserService service) {
        super(service);
    }

    @Override
    public void initializeObjects() {
    }


    @Override
    public User getModelFromFields() {
        User user = getUser();

        user.setUsername(username.getText());
        user.setPassword(password.getText());

        return user;
    }

    private User getUser() {
        if (registry == null)
            return new User();

        return registry;
    }

    @Override
    public void setFieldsFromModel() {
        this.username.setText(this.registry.getUsername());
        this.password.setText(this.registry.getPassword());
    }

    @Override
    public void clearFields() {
        username.clear();
        password.clear();
    }
}
