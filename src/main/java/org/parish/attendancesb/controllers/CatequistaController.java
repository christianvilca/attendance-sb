package org.parish.attendancesb.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.access.Role;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.services.interfaces.RoleService;
import org.parish.attendancesb.services.interfaces.UserService;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CatequistaController extends RegistryController<User> {

    @FXML
    private ComboBox<Catequesis> catequesis;

    @FXML
    private ComboBox<Group> group;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

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
    private CheckBox coordinator;

    @FXML
    private CheckBox manager;

    @Autowired
    private RoleService roleService;

    private final CatequesisService catequesisService;

    private final GroupService groupService;

    public CatequistaController(UserService service, CatequesisService catequesisService, GroupService groupService) {
        super(service);
        this.catequesisService = catequesisService;
        this.groupService = groupService;
    }

    @Override
    public void initializeObjects() {
        catequesis.getItems().addAll(catequesisService.findAll());

        boxPassword.getChildren().removeAll(passwordShow, eyeClose);
        passwordShow.textProperty().bindBidirectional(password.textProperty());
    }

    @FXML
    void changeCatequesis(ActionEvent event) {
        fillGroup();
    }

    @FXML
    void changeManager(ActionEvent event) {
        if (manager.isSelected())
            coordinator.setSelected(true);

        coordinator.setDisable(manager.isSelected());
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

    private void fillGroup() {
        group.setDisable(false);
        group.setValue(null);
        group.getItems().clear();

        group.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Group item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(group.getPromptText());
                } else {
                    setText(item.toString());
                }
            }
        });

        group.getItems().addAll(
                groupService.findAllByCatequesis(
                        catequesis.getSelectionModel().getSelectedItem()
                ));
    }

    @Override
    public User getModelFromFields() {
        User user = getUser();

        System.out.println(user.getGroup());
        user.setGroup(group.getSelectionModel().getSelectedItem());
        System.out.println(user.getGroup());

        user.setLastName(lastName.getText());
        user.setFirstName(firstName.getText());
        user.setUsername(username.getText());
        user.setPassword(password.getText());

        Set<Role> roles = new HashSet<>();
        if (coordinator.isSelected())
            roles.add(roleService.findByName(org.parish.attendancesb.services.Role.COORDINATOR.name()));
        if (manager.isSelected())
            roles.add(roleService.findByName(org.parish.attendancesb.services.Role.MANAGER.name()));

        user.setRoles(roles);

        return user;
    }

    private User getUser() {
        if (registry == null)
            return new User();

        return registry;
    }

    @Override
    public void setFieldsFromModel() {
        this.catequesis.setValue(this.registry.getGroup().getCatequesis());
        fillGroup();
        this.group.setValue(this.registry.getGroup());
        this.group.setDisable(false);
        this.lastName.setText(this.registry.getLastName());
        this.firstName.setText(this.registry.getFirstName());
        this.username.setText(this.registry.getUsername());
        this.password.setText(this.registry.getPassword());
        this.passwordShow.setText(this.registry.getPassword());
        this.coordinator.setSelected(
                this.registry.getRoles().contains(
                        roleService.findByName(org.parish.attendancesb.services.Role.COORDINATOR.name())
                )
        );
        this.manager.setSelected(
                this.registry.getRoles().contains(
                        roleService.findByName(org.parish.attendancesb.services.Role.MANAGER.name())
                )
        );
        coordinator.setDisable(manager.isSelected());
    }

    @Override
    public void clearFields() {
        catequesis.setValue(null);
        group.setValue(null);
        group.setDisable(true);
        firstName.clear();
        lastName.clear();
        username.clear();
        password.clear();
        passwordShow.clear();
        coordinator.setSelected(false);
        manager.setSelected(false);
    }
}
