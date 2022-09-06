package org.parish.attendancesb.services.report;

import net.sf.jasperreports.engine.JRException;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.models.report.AttendanceDetailReport;
import org.parish.attendancesb.models.report.AttendanceReport;
import org.parish.attendancesb.report.Format;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.report.Report;
import org.parish.attendancesb.services.attendance.Resume;
import org.parish.attendancesb.services.attendance.ResumeDetails;
import org.parish.attendancesb.services.interfaces.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AttendanceReportService {

    @Autowired
    private AttendanceService service;

    @Autowired
    private Report report;

    public void exportReport(ReceiverPerson person) {
        Resume resume = service.resume(person);
        AttendanceReport report = new AttendanceReport();
        report.setCode(person.getCode());
        report.setReceiverPerson(person);
        report.setGroup(person.getGroup());

        report.setPresents(resume.getPresents());
        report.setLates(resume.getLates());
        report.setAbsents(resume.getAbsents());
        report.setTotal(resume.getTotal());

        List<AttendanceDetailReport> list = new ArrayList<>();
        List<AttendanceReport> reportList = new ArrayList<>();
        reportList.add(report);

        AttendanceDetailReport detailReport = new AttendanceDetailReport();
        resume.getResumeDetails().forEach(d -> {
            detailReport.setMonth(d.getMonth());
            detailReport.setDay(d.getDay()+"");
            detailReport.setTimeStart(d.getTimeFirst());
            detailReport.setTimeEnd(d.getTimeLast());
            list.add(detailReport);
        });
        report.setList(list);

        System.out.println("Ruta: " + getClass().getResource("/reports/"));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Title", "Asistencias");
        //parameters.put("SUBREPORT_DIR", getClass().getResource("/reports/").toString());
        parameters.put("attendanceList", report.getList());

        try {
            this.report.export(reportList, Jrxml.ATTENDANCE, Format.PDF, parameters );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

}
