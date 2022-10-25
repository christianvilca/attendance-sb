package org.parish.attendancesb.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.controllers.utils.BooleanCell;
import org.parish.attendancesb.controllers.utils.GroupsCatequista;
import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.services.interfaces.CatequistaService;
import org.parish.attendancesb.services.interfaces.RoleService;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CatequistaController extends RegistryController<Catequista> {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private ComboBox<User> user;

    @FXML
    private ComboBox<Group> group;

    @FXML
    private TableView<GroupsCatequista> tableGroups;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> groupColumn;

    @FXML
    private TableColumn<GroupsCatequista, Boolean> coordinator;

    @Autowired
    private RoleService roleService;

    private final CatequesisService catequesisService;

    private final GroupService groupService;

    public CatequistaController(CatequistaService service, CatequesisService catequesisService, GroupService groupService) {
        super(service);
        this.catequesisService = catequesisService;
        this.groupService = groupService;
    }

    @Override
    public void initializeObjects() {
//        group.getItems().add(new Group("- NINGUNO -"));
//        group.getItems().addAll(groupService.findAll());
        fillGroup();

        Set<GroupsCatequista> groupsCatequistas = new HashSet<>();
        this.tableGroups.setItems(FXCollections.observableArrayList(groupsCatequistas));

        setColumnFromModel();
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

        group.getItems().add(new Group("- NINGUNO -"));
        group.getItems().addAll(
                groupService.findAllByCatequesis(
                        catequesisService.getCatequesis()
                ));
    }

    @Override
    public Catequista getModelFromFields() {
        Catequista catequista = getCatequista();

//        System.out.println(catequista.getGroup());
//        catequista.setGroup(group.getSelectionModel().getSelectedItem());
//        System.out.println(catequista.getGroup());

        catequista.setLastName(lastName.getText());
        catequista.setFirstName(firstName.getText());
        catequista.setUser(user.getSelectionModel().getSelectedItem());

        List<Group> groups = new ArrayList<>();
        //tableGroups.getItems().get(1)..forEach(g -> g.);
        catequista.setGroups(groups);

//        catequista.setCatequistaname(username.getText());
//        catequista.setPassword(password.getText());

//        Set<Role> roles = new HashSet<>();
//        if (coordinator.isSelected())
//            roles.add(roleService.findByName(org.parish.attendancesb.services.Role.COORDINATOR.name()));
//        if (manager.isSelected())
//            roles.add(roleService.findByName(org.parish.attendancesb.services.Role.MANAGER.name()));

//        catequista.setRoles(roles);

        return catequista;
    }

    private Catequista getCatequista() {
        if (registry == null)
            return new Catequista();

        return registry;
    }

    @Override
    public void setFieldsFromModel() {
//        this.catequesis.setValue(this.registry.getGroup().getCatequesis());

//        this.group.setValue(this.registry.getGroup());
//        this.group.setDisable(false);
        this.lastName.setText(this.registry.getLastName());
        this.firstName.setText(this.registry.getFirstName());

//        this.tableGroups.setItems(FXCollections.observableArrayList(service.ge));
//        this.tableGroups.refresh();

//        this.username.setText(this.registry.getCatequistaname());
//        this.password.setText(this.registry.getPassword());
//        this.passwordShow.setText(this.registry.getPassword());
//        this.coordinator.setSelected(
//                this.registry.getRoles().contains(
//                        roleService.findByName(org.parish.attendancesb.services.Role.COORDINATOR.name())
//                )
//        );
//        this.manager.setSelected(
//                this.registry.getRoles().contains(
//                        roleService.findByName(org.parish.attendancesb.services.Role.MANAGER.name())
//                )
//        );
//        coordinator.setDisable(manager.isSelected());
    }

    @Override
    public void clearFields() {
//        catequesis.setValue(null);
        group.setValue(null);
        //group.setDisable(true);
        firstName.clear();
        lastName.clear();
//        username.clear();
//        password.clear();
//        passwordShow.clear();
//        coordinator.setSelected(false);
//        manager.setSelected(false);
    }

    @FXML
    void add(ActionEvent event) {
        GroupsCatequista groupsCatequista = new GroupsCatequista();
        groupsCatequista.setId(group.getSelectionModel().getSelectedItem().getId());
        groupsCatequista.setGroup(group.getSelectionModel().getSelectedItem());
        groupsCatequista.setCoordinator(true
                /*this.registry.getUser().getRoles().contains(
                        roleService.findByName(org.parish.attendancesb.services.Role.COORDINATOR.name())
                )*/
        );
        this.tableGroups.getItems().add(groupsCatequista);
        this.tableGroups.refresh();
    }

    @FXML
    void remove(ActionEvent event) {

    }

    public void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));

        Callback<TableColumn<GroupsCatequista, Boolean>, TableCell<GroupsCatequista, Boolean>> booleanCellFactory =
                new Callback<TableColumn<GroupsCatequista, Boolean>, TableCell<GroupsCatequista, Boolean>>() {
                    @Override
                    public TableCell<GroupsCatequista, Boolean> call(TableColumn<GroupsCatequista, Boolean> p) {
                        return new BooleanCell<GroupsCatequista>();
                    }
                };
        coordinator.setCellValueFactory(new PropertyValueFactory<>("coordinator"));
        coordinator.setCellFactory(booleanCellFactory);
    }
}
