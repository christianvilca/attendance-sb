package org.parish.attendancesb;

import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import static org.parish.attendancesb.AttendanceFXApplication.*;

// Set up our JavaFX Stage when it's ready
@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage =event.getStage();
    }
}
