package org.parish.attendancesb;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AttendanceSbApplication {

	public static void main(String[] args) {
		Application.launch(AttendanceFXApplication.class, args);
	}

}
