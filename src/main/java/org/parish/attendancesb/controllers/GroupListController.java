package org.parish.attendancesb.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.controllers.abstractions.RegistryListController;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.parish.attendancesb.view.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Controller
public class GroupListController extends RegistryListController<Group> {

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> catequesis;

    @FXML
    private TableColumn<?, ?> name;

    @Lazy
    @Autowired
    private StageManager stageManager;

    private GroupCatequistaListController groupCatequistaListController;

    public GroupListController(
            GroupController controller,
            GroupService service,
            GroupCatequistaListController groupCatequistaListController
    ) {
        super(controller, service);
        this.groupCatequistaListController = groupCatequistaListController;
    }

    @Override
    public void initializeObjects() {
        setModal(FxmlView.GROUP);
        setReport(Jrxml.GROUP_LIST);
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
            groupCatequistaListController.setGroup(getRow());
            stageManager.sceneModal(FxmlView.GROUP_CATEQUISTA_LIST);
        });
        return menu;
    }

    @Override
    public void setColumnFromModel() {
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.catequesis.setCellValueFactory(new PropertyValueFactory<>("catequesis"));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

}
