package org.parish.attendancesb.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.parish.attendancesb.config.StageManager;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.services.MainService;
import org.parish.attendancesb.services.interfaces.CatequesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class CatequesisViewController implements Initializable {

    @FXML
    private Label lblDay;

    @FXML
    private Label lblName;

    @FXML
    private Label lblReceiverPersonType;

    @FXML
    private Label lblTimeEnd;

    @FXML
    private Label lblTimeStart;

    @FXML
    private Label lblTolerance;

    @Autowired
    private MainService mainService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Catequesis catequesis = mainService.getCatequesis();
        this.lblName.setText(catequesis.getName());
        this.lblDay.setText(catequesis.getDay());
        this.lblTimeStart.setText(catequesis.getTimeStart().toAMPM());
        this.lblTimeEnd.setText(catequesis.getTimeEnd().toAMPM());
        this.lblTolerance.setText(String.valueOf(catequesis.getTolerance()));
        this.lblReceiverPersonType.setText(catequesis.getReceiverPersonType());
    }
}
