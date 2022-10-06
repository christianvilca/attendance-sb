package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.controllers.abstractions.RegistryListController;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.stereotype.Component;

@Component
public class CatequesisListController extends RegistryListController<Catequesis> {

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> day;

    @FXML
    private TableColumn<?, ?> timeStart;

    @FXML
    private TableColumn<?, ?> timeEnd;

    @FXML
    private TableColumn<?, ?> tolerance;

    public CatequesisListController(CatequesisController controller, CatequesisService service) {
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
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.day.setCellValueFactory(new PropertyValueFactory<>("day"));
        this.timeStart.setCellValueFactory(new PropertyValueFactory<>("timeStart"));
        this.timeEnd.setCellValueFactory(new PropertyValueFactory<>("timeEnd"));
        this.tolerance.setCellValueFactory(new PropertyValueFactory<>("tolerance"));
    }

}
