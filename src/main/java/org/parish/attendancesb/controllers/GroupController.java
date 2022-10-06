package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.springframework.stereotype.Component;

@Component
public class GroupController extends RegistryController<Group> {

    @FXML
    private ComboBox<Catequesis> catequesis;

    @FXML
    private TextField name;

    private CatequesisService catequesisService;

    public GroupController(GroupService service, CatequesisService catequesisService) {
        super(service);
        this.catequesisService = catequesisService;
    }

    @Override
    public void initializeObjects() {
        catequesis.getItems().addAll(catequesisService.findAll());
    }

    @Override
    public Group getModelFromFields() {
        Group group = getGroup();

        group.setCatequesis(catequesis.getSelectionModel().getSelectedItem());
        group.setName(name.getText());

        return group;
    }

    private Group getGroup() {
        if (registry == null)
            return new Group();

        return registry;
    }

    @Override
    public void setFieldsFromModel() {
        this.catequesis.setValue(this.registry.getCatequesis());
        this.name.setText(this.registry.getName());
    }

    @Override
    public void clearFields() {
        catequesis.setValue(null);
        name.clear();
    }
}
