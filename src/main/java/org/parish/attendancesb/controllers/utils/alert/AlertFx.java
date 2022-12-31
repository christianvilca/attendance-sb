package org.parish.attendancesb.controllers.utils.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertFx {

    private AlertFx() {
    }

    public static void information(String description) {
        final String INFORMATION = "Información";

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(INFORMATION);
        alert.setContentText(description);
        alert.showAndWait();
    }

    public static void error(String description) {
        final String ERROR = "Error";

        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(ERROR);
        alert.setContentText(description);
        alert.showAndWait();
    }

    public static void warning(String description) {
        final String WARNING = "Warning";

        Alert alert = new Alert(AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(WARNING);
        alert.setContentText(description);
        alert.showAndWait();
    }

    public static boolean yesno(String description) {
        final String WARNING = "Warning";

        Alert alert = new Alert(AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(WARNING);
        alert.setContentText(description);

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        Button yesButton = (Button) alert.getDialogPane().lookupButton( ButtonType.YES );
        yesButton.setDefaultButton( false );

        Button noButton = (Button) alert.getDialogPane().lookupButton( ButtonType.NO );
        noButton.setDefaultButton( true );

        final Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.YES;
    }

}
