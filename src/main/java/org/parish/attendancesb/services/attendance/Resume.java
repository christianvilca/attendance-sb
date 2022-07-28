package org.parish.attendancesb.services.attendance;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
//import org.parish.attendance.model.*;
import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.AttendanceDate;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.ReceiverPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
@RequiredArgsConstructor
public class Resume {
    private ReceiverPerson receiverPerson;
    private int presents;
    private int lates;
    private int absents;
    private int total;
    private List<ResumeDetails> resumeDetails;

    private Catequesis catequesis;
    private AttendanceDate attendanceDate;
    private List<AttendanceDate> attendanceDateList;
    private List<Attendance> attendanceListDay;
    private List<Attendance> attendanceList;

    public Resume(Catequesis catequesis, ReceiverPerson receiverPerson, List<AttendanceDate> attendanceDateList, List<Attendance> attendanceList) {
        assert attendanceDateList != null && attendanceList != null;

        this.catequesis = catequesis;
        this.receiverPerson = receiverPerson;
        this.attendanceDateList = attendanceDateList;
        System.out.println("this.attendanceDateList: "+ this.attendanceDateList);

        this.attendanceList = attendanceList;

        this.total = this.attendanceDateList.size();

        this.resumeDetails = new ArrayList<>();
        //this.initialize();
        int item = 0;
        for (AttendanceDate attendanceDate : attendanceDateList) {
            this.attendanceListDay = attendanceList;
            this.filter(attendanceDate);
            this.add(++item, attendanceDate, this.attendanceListDay, this.catequesis);
            this.details();
        }
    }
//
//    public void initialize(){
//        int item = 0;
//
//        for (AttendanceDate attendanceDate : attendanceDateList) {
//            this.attendanceListDay = this.attendanceList;
//
//            System.out.println("this.attendanceList: " + this.attendanceList.get(item++));
//            System.out.println("this.attendanceListDay: " + this.attendanceListDay.get(item++));
//            this.filter(attendanceDate);
//            //List<Attendance> b = this.attendanceListDay.stream().filter(a -> a.getDate() == attendanceDate.getDate()).collect(Collectors.toList());
//            this.add(++item, attendanceDate, this.attendanceListDay, this.catequesis);
//            this.details();
//        }
//    }

    private void filter(AttendanceDate attendanceDate) {
        this.attendanceListDay = this.attendanceListDay.stream().filter(a -> a.getDateTime().getDate().equals(attendanceDate.getDate())).collect(Collectors.toList());
    }

    public void add(int item, AttendanceDate attendanceDate, List<Attendance> attendanceListDay, Catequesis catequesis) {
        this.resumeDetails.add(
                new ResumeDetails(
                        item,
                        attendanceDate,
                        attendanceListDay,
                        catequesis
                )
        );
    }

    private void details() {
        List<ResumeDetails> resumeDetails;
        resumeDetails = this.resumeDetails;
        this.presents = resumeDetails.stream().filter(r -> r.getTypeFirst().equals(AttendanceType.PRESENT)).collect(Collectors.toList()).size();
        resumeDetails = this.resumeDetails;
        this.lates = resumeDetails.stream().filter(r -> r.getTypeFirst().equals(AttendanceType.LATE)).collect(Collectors.toList()).size();
        resumeDetails = this.resumeDetails;
        this.absents = resumeDetails.stream().filter(r -> r.getTypeFirst().equals(AttendanceType.ABSENT) && r.getTypeLast().equals(AttendanceType.ABSENT)).collect(Collectors.toList()).size();
    }

}
