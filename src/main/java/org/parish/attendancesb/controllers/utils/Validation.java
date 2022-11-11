package org.parish.attendancesb.controllers.utils;

public class Validation {

    private String field;
    private String warnning;
    private boolean valid;

    public Validation(String field) {
        this.field = field;
    }

    protected String getField() {
        return field;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getWarnning(){
        return warnning;
    }

    public void setWarnning(String warnning) {
        this.warnning = warnning;
    }
}
