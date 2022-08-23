package org.parish.attendancesb.config;

import java.io.IOException;
import java.io.StringWriter;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import org.parish.attendancesb.logging.ExceptionWriter;

@Configuration
public class AppJavaConfig {

    @Autowired
    SpringFXMLLoader springFXMLLoader;

    /**
     * Useful when dumping stack trace to a string for logging.
     * @return ExceptionWriter contains logging utility methods
     */
    @Bean
    @Scope("prototype")
    public ExceptionWriter exceptionWriter() {
        return new ExceptionWriter(new StringWriter());
    }

    @Bean
    @Lazy(value = true) //Stage only created after Spring context bootstap
    public StageManager stageManager(Stage stage) throws IOException {
        return new StageManager(springFXMLLoader, stage);
    }

}