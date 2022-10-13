package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.controllers.abstractions.RegistrySearchController;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.springframework.stereotype.Component;

@Component
public class CatequesisSearchController extends RegistrySearchController<Catequesis> {

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> name;

    public CatequesisSearchController(CatequesisService service) {
        super(service);
    }

    @Override
    public void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}
