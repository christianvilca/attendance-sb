package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.controllers.abstractions.RegistryListController;
import org.parish.attendancesb.models.access.User;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.services.interfaces.UserService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
public class CatequistaListController extends RegistryListController<User> {

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> catequesis;

    @FXML
    private TableColumn<?, ?> group;

    @FXML
    private TableColumn<?, ?> lastName;

    @FXML
    private TableColumn<?, ?> firstName;

    public CatequistaListController(CatequistaController controller, UserService service) {
        super(controller, service);
    }

    @Override
    public void initializeObjects() {
        setModal(FxmlView.CATEQUISTA);
        setReport(Jrxml.GROUP_LIST);
    }

    @Override
    public void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.catequesis.setCellValueFactory(new PropertyValueFactory<>("catequesis"));
        this.group.setCellValueFactory(new PropertyValueFactory<>("group"));
        this.lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    }

}
