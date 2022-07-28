package org.parish.attendancesb.services.attendance;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
//import org.parish.attendance.model.Attendance;
//import org.parish.attendance.model.AttendanceDate;
//import org.parish.attendance.model.AttendanceType;
//import org.parish.attendance.model.Catequesis;
import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.AttendanceDate;
import org.parish.attendancesb.models.Catequesis;

import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
public class ResumeDetails {
    @NonNull
    private int item;
    @NonNull
    private String month;
    @NonNull
    private int day;
    @NonNull
    private String hourFirst;
    @NonNull
    private AttendanceType typeFirst;
    @NonNull
    private String hourLast;
    @NonNull
    private AttendanceType typeLast;

    private List<Attendance> attendanceListDay;
    private Catequesis catequesis;

    public ResumeDetails(int item, AttendanceDate attendanceDate, List<Attendance> attendanceListDay, Catequesis catequesis) {
        assert attendanceListDay != null && attendanceListDay.size() > 0;
        this.item = item;
        this.month = attendanceDate.getMonth();
        this.day = attendanceDate.getDay();

        this.attendanceListDay = attendanceListDay;
        this.catequesis = catequesis;

        this.getFirst();
        this.getLast();
    }

    private void getFirst(){
        if (isZero()){
            this.hourFirst = AttendanceType.ABSENT.toString();
            this.typeFirst = AttendanceType.ABSENT;
        } else if (isOne()){
            if (this.first().isBeforeMiddle()){
                this.hourFirst = this.first().toAMPM();
                if (this.first().isBeforeStart()){
                    this.typeFirst = AttendanceType.PRESENT;
                } else {
                    this.typeFirst = AttendanceType.LATE;
                }
            } else {
                this.hourFirst = AttendanceType.WITHOUT.toString();
                this.typeFirst = AttendanceType.WITHOUT;
            }
        } else {
            if (this.first().isBeforeMiddle()){
                this.hourFirst = this.first().toAMPM();
                if (this.first().isBeforeStart()){
                    this.typeFirst = AttendanceType.PRESENT;
                } else {
                    this.typeFirst = AttendanceType.LATE;
                }
            } else {
                this.hourFirst = this.first().toAMPM();
                this.typeFirst = AttendanceType.ABSENT;
            }
        }
    }

    private void getLast(){
        if (isZero()){
            this.hourLast = AttendanceType.ABSENT.toString();
            this.typeLast = AttendanceType.ABSENT;
        } else if (isOne()){
            if (this.last().isAfterMiddle()){
                this.hourLast = this.last().toAMPM();
                this.typeLast = AttendanceType.PRESENT;
            } else {
                this.hourLast = AttendanceType.WITHOUT.toString();
                this.typeLast = AttendanceType.WITHOUT;
            }
        } else {
            if (this.last().isAfterMiddle()){
                this.hourLast = this.last().toAMPM();
                this.typeLast = AttendanceType.PRESENT;
            } else {
                this.hourLast = this.last().toAMPM();
                this.typeLast = AttendanceType.ABSENT;
            }
        }
    }

    private Attendance first() {
        return attendanceListDay.get(0);
    }

    private Attendance last() {
        return attendanceListDay.get(attendanceListDay.size() - 1);
    }

    private boolean isZero(){
        return attendanceListDay == null || attendanceListDay.size() == 0;
    }

    private boolean isOne(){
        return attendanceListDay.size() == 1;
    }

    private boolean isMany(){
        return attendanceListDay.size() > 1;
    }
}
