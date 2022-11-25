package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.parish.attendancesb.controllers.abstractions.RegistryController;
import org.parish.attendancesb.controllers.utils.ValidationList;
import org.parish.attendancesb.controllers.utils.ValidationTextField;
import org.parish.attendancesb.controllers.utils.ValidationType;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.services.SessionService;
import org.parish.attendancesb.services.interfaces.GroupService;
import org.springframework.stereotype.Component;

@Component
public class GroupController extends RegistryController<Group> {

    @FXML
    private TextField name;

    private final SessionService sessionService;

    public GroupController(GroupService service, SessionService sessionService) {
        super(service);
        this.sessionService = sessionService;
    }

    @Override
    public void initializeObjects() {
    }

    @Override
    public Group getModelFromFields() {
        Group group = getGroup();

        group.setCatequesis(sessionService.getCatequesis());
        group.setName(name.getText());

        return group;
    }

    private Group getGroup() {
        if (registry == null)
            return new Group();

        return registry;
    }

    @Override
    protected boolean isValid() {
        return ValidationList.isValid(
                new ValidationTextField("Nombre", name, ValidationType.TEXT)
        );
    }

    @Override
    public void setFieldsFromModel() {
        this.name.setText(this.registry.getName());
    }

    @Override
    public void clearFields() {
        name.clear();
    }
}
