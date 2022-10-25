package org.parish.attendancesb.controllers.utils;

import javafx.scene.control.CheckBox;
import lombok.Data;
import org.parish.attendancesb.models.Group;

@Data
public class GroupsCatequista {

    private Integer id;
    private Group group;
    private Boolean coordinator;
}
