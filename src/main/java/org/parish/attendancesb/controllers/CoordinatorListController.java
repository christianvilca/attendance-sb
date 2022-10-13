package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.controllers.abstractions.RegistryListController;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.stereotype.Component;

@Component
public class CoordinatorListController extends RegistryListController<Catequesis> {

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> catequesis;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> user;

    public CoordinatorListController(CatequesisController controller, CatequesisService service) {
        super(controller, service);
    }

    @Override
    public void initializeObjects() {
        setModal(FxmlView.CATEQUESIS);
        setReport(Jrxml.CATEQUESIS_LIST);
    }

    @Override
    public void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.catequesis.setCellValueFactory(new PropertyValueFactory<>("catequesis"));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.user.setCellValueFactory(new PropertyValueFactory<>("user"));
    }

}
