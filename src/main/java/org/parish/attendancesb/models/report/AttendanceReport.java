package org.parish.attendancesb.models.report;

import lombok.Data;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.ReceiverPerson;

import java.util.List;

@Data
public class AttendanceReport {
    private String code;
    private ReceiverPerson receiverPerson;
    private Group group;
    private List<AttendanceDetailReport> list;
    private Integer presents;
    private Integer lates;
    private Integer absents;
    private Integer total;
}
