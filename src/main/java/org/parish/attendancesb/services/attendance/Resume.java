package org.parish.attendancesb.services.attendance;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.AttendanceDate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
@RequiredArgsConstructor
public class Resume {
    private List<AttendanceDate> attendanceDateList;
    private List<Attendance> attendanceList;

    private List<ResumeDetails> resumeDetails;

    public Resume(List<AttendanceDate> attendanceDateList, List<Attendance> attendanceList) {
        assert attendanceDateList != null && attendanceList != null;

        this.attendanceDateList = attendanceDateList;
        this.attendanceList = attendanceList;
    }

    public void generate() {
        this.resumeDetails = new ArrayList<>();

        this.attendanceDateList.forEach(a -> this.add(a));
    }

    private List<Attendance> filter(AttendanceDate attendanceDate) {
        return this.attendanceList.stream().filter(a -> a.getDateTime().getDate().equals(attendanceDate.getDate())).collect(Collectors.toList());
    }

    private void add(AttendanceDate attendanceDate) {
        this.resumeDetails.add(
                new ResumeDetails(
                        attendanceDate,
                        this.filter(attendanceDate)
                )
        );
    }

    public int getPresents() {
        return resumeDetails.stream().filter(r -> r.getTypeFirst().equals(AttendanceType.PRESENT)).collect(Collectors.toList()).size();
    }

    public int getLates() {
        return resumeDetails.stream().filter(r -> r.getTypeFirst().equals(AttendanceType.LATE)).collect(Collectors.toList()).size();
    }

    public int getAbsents() {
        return resumeDetails.stream().filter(r -> r.getTypeFirst().equals(AttendanceType.ABSENT) && r.getTypeLast().equals(AttendanceType.ABSENT)).collect(Collectors.toList()).size();
    }

    public int getTotal() {
        return this.attendanceDateList.size();
    }

}
