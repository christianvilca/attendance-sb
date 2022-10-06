package org.parish.attendancesb.models.report;

import lombok.Data;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.ReceiverPerson;

import java.util.List;

@Data
public class AttendanceReport {
    private String code;
    private ReceiverPerson receiverPerson;
    private Group group;
    private List<AttendanceDetailReport> detail;
    private List<AttendanceChartReport> chart;
    private JRBeanCollectionDataSource chartDataSource;

    public JRBeanCollectionDataSource getChartDataSource() {
        return new JRBeanCollectionDataSource(chart, false);
    }

}
