package org.parish.attendancesb.models;

public enum ReceiverPersonType {
    CHILD("Niño(a)", "Niños"),
    YOUNG("Joven", "Jovenes"),
    CONFIRMANTE("Confirmante", "Confirmantes");

    private String singular;

    private String plural;

    ReceiverPersonType(String singular, String plural) {
        this.singular = singular;
        this.plural = plural;
    }

    public String getSingular() {
        return singular;
    }

    public String getPlural() {
        return plural;
    }
}
