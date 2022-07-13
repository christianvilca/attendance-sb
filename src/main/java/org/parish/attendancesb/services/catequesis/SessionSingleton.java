package org.parish.attendancesb.services.catequesis;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SessionSingleton {
    private static SessionSingleton singleton = new SessionSingleton();

    private int idCatequesis;

    public static SessionSingleton instance() {
        return singleton;
    }

    private SessionSingleton() {

    }

    public static void main(String[] args) {
        // Nunca usar
        // AccessSingleton.setIdCatequesis(1);
        // AccessSingleton.getIdCatequesis()

        SessionSingleton.instance().setIdCatequesis(1);
        System.out.println(SessionSingleton.instance().getIdCatequesis());
    }
}
