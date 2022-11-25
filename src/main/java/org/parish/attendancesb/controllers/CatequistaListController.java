package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.controllers.abstractions.RegistryListController;
import org.parish.attendancesb.models.Catequista;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.services.interfaces.CatequistaService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
public class CatequistaListController extends RegistryListController<Catequista> {

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> lastName;

    @FXML
    private TableColumn<?, ?> firstName;

    public CatequistaListController(CatequistaController controller, CatequistaService service) {
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
        this.lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    }

}
