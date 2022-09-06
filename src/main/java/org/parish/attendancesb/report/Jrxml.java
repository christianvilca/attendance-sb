package org.parish.attendancesb.report;

import java.util.ResourceBundle;

public enum Jrxml {

    ATTENDANCE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getJrxmlFile() {
            return "/reports/Attendance.jrxml";
        }
    },
    CATEQUESIS_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getJrxmlFile() {
            return "/reports/CatequesisList.jrxml";
        }
    },
    RECEIVER_PERSON_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getJrxmlFile() {
            return "/reports/ReceiverPerson.jrxml";
        }
    };

    public abstract String getTitle();

    public abstract String getJrxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
