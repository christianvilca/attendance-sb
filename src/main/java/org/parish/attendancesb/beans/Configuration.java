package org.parish.attendancesb.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

    @Bean("lblTitle")
    private String Title(){
        return "Bienvenido a Spring";
    }
}
