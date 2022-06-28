package org.parish.attendancesb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AttendanceSbApplication extends Application {

    public static ConfigurableApplicationContext applicationContext;
    public static Parent rootNode;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Escanea toda la aplicacion y busca todas las dependencias
        // las dependencias se cargan ene el "applicationContext"
        applicationContext = SpringApplication.run(AttendanceSbApplication.class);
        FXMLLoader loader = new FXMLLoader(AttendanceSbApplication.class.getResource("/index.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(loader.load(), 800, 800, false, SceneAntialiasing.BALANCED);

        primaryStage.setScene(scene); // al escenario se le asigna la escena
        primaryStage.show(); // escenario muestrate
    }

    public static void main(String[] args) {
        launch(args);
    }
}
