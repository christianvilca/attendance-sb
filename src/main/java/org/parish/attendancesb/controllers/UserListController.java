package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.controllers.abstractions.RegistryListController;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.services.interfaces.UserService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.stereotype.Component;

@Component
public class UserListController extends RegistryListController<User> {

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> username;

    public UserListController(UserController controller, UserService service) {
        super(controller, service);
    }

    @Override
    public void initializeObjects() {
        setModal(FxmlView.USER);
        setReport(Jrxml.USER_LIST);
    }

    @Override
    public void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.username.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

}
