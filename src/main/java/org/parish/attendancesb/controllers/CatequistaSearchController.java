package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.controllers.abstractions.RegistrySearchController;
import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.services.interfaces.CatequistaService;
import org.springframework.stereotype.Controller;

@Controller
public class CatequistaSearchController extends RegistrySearchController<Catequista> {

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> firstName;

    @FXML
    private TableColumn<?, ?> lastName;

    protected CatequistaSearchController(CatequistaService service) {
        super(service);
    }

    @Override
    protected void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    }

}