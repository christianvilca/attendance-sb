package org.parish.attendancesb.controllers;

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
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.access.Credential;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.services.Role;
import org.parish.attendancesb.services.interfaces.CatequistaService;
import org.parish.attendancesb.services.interfaces.CredentialService;
import org.parish.attendancesb.services.interfaces.RoleService;
import org.parish.attendancesb.services.interfaces.UserService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserController extends RegistryController<User> {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

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

    private RoleService roleService;

    private CredentialService credentialService;

    private CatequistaService catequistaService;

    private UserService service;

    public UserController(UserService service, RoleService roleService, CredentialService credentialService, CatequistaService catequistaService) {
        super(service);

        this.service = service;
        this.credentialService = credentialService;
        this.roleService = roleService;
        this.catequistaService = catequistaService;
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

        Set<Credential> credentials = new HashSet<>();

        if (user.getCredentials() != null) {
            credentials.addAll(
                    user.getCredentials().stream().filter(credential ->
                            credential.getRole().getName() == Role.COORDINATOR.name() &&
                                    credential.getCatequesis() != null
                    ).collect(Collectors.toList())
            );
        }
        LOGGER.info(roleService.findByName(org.parish.attendancesb.services.Role.MANAGER.name()));
        if (manager.isSelected()) {
            Credential credential = new Credential(
                    user,
                    roleService.findByName(org.parish.attendancesb.services.Role.MANAGER.name())
            );
            credentialService.save(credential);
            credentials.add(credential);
            credential = new Credential(
                    user,
                    roleService.findByName(org.parish.attendancesb.services.Role.COORDINATOR.name())
            );
            credentials.add(credential);
            credentialService.save(credential);
        }
        //credentialService.save(credentials);
        user.setCredentials(credentials);
        LOGGER.info(credentials);
        LOGGER.info(user.getCredentials());

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
                //service.authorize(Role.MANAGER.name())
                this.registry.getCredentials().stream().anyMatch(credential -> credential.getRole().getName().contains(Role.MANAGER.name()))
        );
    }

    @Override
    public void clearFields() {
        username.clear();
        password.clear();
        passwordShow.clear();
        manager.setSelected(false);
    }
}
