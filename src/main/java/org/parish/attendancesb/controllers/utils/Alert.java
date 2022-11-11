package org.parish.attendancesb.controllers.utils;

public class Alert {

    private Alert() {
    }

    public static void information(String description) {
        final String INFORMATION = "Información";

        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(INFORMATION);
        alert.setContentText(description);
        alert.showAndWait();
    }

    public static void error(String description) {
        final String ERROR = "Error";

        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(ERROR);
        alert.setContentText(description);
        alert.showAndWait();
    }

    public static void warning(String description) {
        final String WARNING = "Warning";

        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(WARNING);
        alert.setContentText(description);
        alert.showAndWait();
    }

}
