package org.parish.attendancesb.models.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendanceChartReport {
    private String name;
    private Integer amount;
}
