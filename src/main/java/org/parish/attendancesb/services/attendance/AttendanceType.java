package org.parish.attendancesb.services.attendance;

public enum AttendanceType {
    PRESENT("PRESENTE"),
    LATE("TARDE"),
    ABSENT("FALTA"),
    WITHOUT("NO REGISTRO");

    private String type;

    private AttendanceType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "" + type;
    }
}
