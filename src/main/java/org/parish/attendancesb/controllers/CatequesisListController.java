package org.parish.attendancesb.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.controllers.abstractions.RegistryListController;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @Lazy
    @Autowired
    private StageManager stageManager;


    private CatequesisCatequistaListController catequesisCatequistaListController;

    public CatequesisListController(
            CatequesisController controller,
            CatequesisService service,
            CatequesisCatequistaListController catequesisCatequistaListController
    ) {
        super(controller, service);
        this.catequesisCatequistaListController = catequesisCatequistaListController;
    }

    @Override
    public void initializeObjects() {
        setModal(FxmlView.CATEQUESIS);
        setReport(Jrxml.CATEQUESIS_LIST);
        this.addAllMenu();
    }

    private void addAllMenu() {
        addAllMenu(
                new SeparatorMenuItem(),
                getMenuCatequistas()
        );
    }

    private MenuItem getMenuCatequistas() {
        MenuItem menu = new MenuItem("Catequistas");
        menu.setOnAction((ActionEvent e) -> {
            catequesisCatequistaListController.setCatequesis(getRow());
            stageManager.sceneModal(FxmlView.CATEQUESIS_CATEQUISTA_LIST);
        });
        return menu;
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
