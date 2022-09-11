package org.parish.attendancesb.services.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.models.report.AttendanceChartReport;
import org.parish.attendancesb.models.report.AttendanceDetailReport;
import org.parish.attendancesb.models.report.AttendanceReport;
import org.parish.attendancesb.report.Format;
import org.parish.attendancesb.report.Jrxml;
import org.parish.attendancesb.report.Report;
import org.parish.attendancesb.services.attendance.Resume;
import org.parish.attendancesb.services.interfaces.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private Report reportService;

    private AttendanceReport attendanceReport;

    public void exportReport(ReceiverPerson person) {
        setAttendanceReport(person);
        generate();
    }

    private void generate() {
        List<AttendanceReport> reportList = new ArrayList<>();
        reportList.add(attendanceReport);

        try {
            reportService.export(reportList, Jrxml.ATTENDANCE, getParameters());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private void setAttendanceReport(ReceiverPerson person) {
        Resume resume = service.resume(person);
        attendanceReport = new AttendanceReport();
        attendanceReport.setCode(person.getCode());
        attendanceReport.setReceiverPerson(person);
        attendanceReport.setGroup(person.getGroup());
        setDetails(resume);
        setChart(resume);
    }

    private Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Title", "Asistencias");
        //parameters.put("SUBREPORT_DIR", getClass().getResource("/reports/").toString());
        parameters.put("attendanceList", attendanceReport.getDetail());
        return parameters;
    }

    private void setDetails(Resume resume) {
        List<AttendanceDetailReport> list = new ArrayList<>();
        resume.getResumeDetails().forEach(d -> {
            AttendanceDetailReport detail = new AttendanceDetailReport();
            detail.setMonth(d.getMonth());
            detail.setDay(d.getDay() + "");
            detail.setTimeStart(d.getTimeFirst());
            detail.setTimeEnd(d.getTimeLast());
            list.add(detail);
        });
        attendanceReport.setDetail(list);
    }

    private void setChart(Resume resume) {
        List<AttendanceChartReport> chart = new ArrayList<>();
        chart.add(new AttendanceChartReport("Asistencias", resume.getPresents()));
        chart.add(new AttendanceChartReport("Tardanzas", resume.getLates()));
        chart.add(new AttendanceChartReport("Faltas", resume.getAbsents()));
        chart.add(new AttendanceChartReport("Total", resume.getTotal()));
        attendanceReport.setChart(chart);
    }

}
