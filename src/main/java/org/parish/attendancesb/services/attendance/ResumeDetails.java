package org.parish.attendancesb.services.attendance;

import lombok.*;
import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.AttendanceDate;

import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
public class ResumeDetails {
    private AttendanceDate attendanceDate;
    private List<Attendance> attendanceListDay;

    private String timeFirst;
    private AttendanceType typeFirst;
    private String timeLast;
    private AttendanceType typeLast;

    public ResumeDetails(AttendanceDate attendanceDate, List<Attendance> attendanceListDay) {
        this.attendanceDate = attendanceDate;
        this.attendanceListDay = attendanceListDay;

        this.getFirst();
        this.getLast();
    }

    public String getMonth() {
        return attendanceDate.getMonth();
    }

    public int getDay() {
        return attendanceDate.getDay();
    }

    private void getFirst() {
        this.timeFirst = AttendanceType.WITHOUT.toString();
        this.typeFirst = AttendanceType.WITHOUT;

        if (isZero()) {
            this.timeFirst = AttendanceType.ABSENT.toString();
            this.typeFirst = AttendanceType.ABSENT;
        } else {
            if (this.first().isBeforeMiddle()) {
                this.timeFirst = this.first().toAMPM();
                if (this.first().isBeforeStart()) {
                    this.typeFirst = AttendanceType.PRESENT;
                } else {
                    this.typeFirst = AttendanceType.LATE;
                }
            }
        }
    }

    private void getLast() {
        this.timeLast = AttendanceType.WITHOUT.toString();
        this.typeLast = AttendanceType.WITHOUT;

        if (isZero()) {
            this.timeLast = AttendanceType.ABSENT.toString();
            this.typeLast = AttendanceType.ABSENT;
        } else{
            if (!this.last().isBeforeMiddle()) {
                this.timeLast = this.last().toAMPM();
                this.typeLast = AttendanceType.PRESENT;
            }
        }
    }

    private Attendance first() {
        return attendanceListDay.get(0);
    }

    private Attendance last() {
        return attendanceListDay.get(attendanceListDay.size() - 1);
    }

    private boolean isZero() {
        return attendanceListDay == null || attendanceListDay.size() == 0;
    }

}
