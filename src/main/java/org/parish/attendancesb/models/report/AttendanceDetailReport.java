package org.parish.attendancesb.models.report;

import lombok.Data;

@Data
public class AttendanceDetailReport {
    private String number;
    private String month;
    private String day;
    private String timeStart;
    private String timeEnd;
}
