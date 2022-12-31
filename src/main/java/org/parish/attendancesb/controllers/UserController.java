package org.parish.attendancesb.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kordamp.ikonli.javafx.FontIcon;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.controllers.utils.validation.ValidationList;
import org.parish.attendancesb.controllers.utils.validation.ValidationTextField;
import org.parish.attendancesb.controllers.utils.validation.ValidationType;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.models.access.Role;
import org.parish.attendancesb.models.access.RoleType;
import org.parish.attendancesb.services.interfaces.RoleService;
import org.parish.attendancesb.services.interfaces.UserService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserController extends RegistryController<User> {

    final Logger logger = LogManager.getLogger(this.getClass());

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField passwordShow;

    @FXML
    private HBox boxPassword;

    @FXML
    private FontIcon eyeClose;

    @FXML
    private FontIcon eyeOpen;

    @FXML
    private CheckBox manager;

    @FXML
    private CheckBox coordinator;
    private final RoleService roleService;

    public UserController(UserService service, RoleService roleService) {
        super(service);
        this.roleService = roleService;
    }

    @Override
    protected boolean isValid() {
        return ValidationList.isValid(
                new ValidationTextField("Usuario", username, ValidationType.TEXT)
        );
    }

    @Override
    public void initializeObjects() {
        boxPassword.getChildren().removeAll(passwordShow, eyeClose);
        passwordShow.textProperty().bindBidirectional(password.textProperty());
    }

    @FXML
    void show(MouseEvent event) {
        password.setVisible(false);
        passwordShow.setVisible(true);
        eyeOpen.setVisible(false);
        eyeClose.setVisible(true);

        boxPassword.getChildren().removeAll(password, eyeOpen);
        boxPassword.getChildren().addAll(passwordShow, eyeClose);
        passwordShow.requestFocus();
    }

    @FXML
    void hire(MouseEvent event) {
        password.setVisible(true);
        passwordShow.setVisible(false);
        eyeOpen.setVisible(true);
        eyeClose.setVisible(false);

        boxPassword.getChildren().removeAll(passwordShow, eyeClose);
        boxPassword.getChildren().addAll(password, eyeOpen);
        password.requestFocus();
    }

    @Override
    public User getModelFromFields() {
        User user = getUser();

        user.setUsername(username.getText());

        Set<Role> roles = new HashSet<>();

        if (manager.isSelected())
            roles.add(roleService.findByName(RoleType.MANAGER.name()));

        if (coordinator.isSelected())
            roles.add(roleService.findByName(RoleType.COORDINATOR.name()));


        user.setRoles(roles);

        if (password.getText().trim().equals("")) {
            user.setPassword(username.getText());
            return user;
        }

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

        this.manager.setSelected(
                this.registry.getRoles().contains(
                        roleService.findByName(RoleType.MANAGER.name())
                )
        );

        coordinator.setDisable(manager.isSelected());

        this.coordinator.setSelected(
                this.registry.getRoles().contains(
                        roleService.findByName(RoleType.COORDINATOR.name())
                )
        );
    }

    @Override
    public void clearFields() {
        username.clear();
        password.clear();
        passwordShow.clear();
        manager.setSelected(false);
    }

    @FXML
    void changeManager(ActionEvent event) {
        if (manager.isSelected())
            coordinator.setSelected(true);
        coordinator.setDisable(manager.isSelected());
    }
}
