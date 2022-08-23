package org.parish.attendancesb;

import javafx.application.Application;
import javafx.stage.Stage;
import org.parish.attendancesb.config.FxmlView;
import org.parish.attendancesb.config.StageManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AttendanceSbApplication extends Application {

    protected ConfigurableApplicationContext springContext;
    protected StageManager stageManager;

    @Override
    public void init() throws Exception {
        springContext = springBootApplicationContext();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stageManager = springContext.getBean(StageManager.class, stage);
        displayInitialScene();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    protected void displayInitialScene() {
        stageManager.switchScene(FxmlView.MAIN);
    }

    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(AttendanceSbApplication.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        return builder.run(args);
    }

    public static void main(final String[] args) {
        launch(args);
    }

}
