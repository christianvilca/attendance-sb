package org.parish.attendancesb.report;

import java.util.ResourceBundle;

public enum Jrxml {

    ATTENDANCE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getExport() {
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
            return getStringFromResourceBundle("catequesis_list.report.title");
        }

        @Override
        public String getExport() {
            return getStringFromResourceBundle("catequesis_list.report.export");
        }

        @Override
        public String getJrxmlFile() {
            return "/reports/CatequesisList.jrxml";
        }
    },
    GROUP_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("group_list.report.title");
        }

        @Override
        public String getExport() {
            return getStringFromResourceBundle("group_list.report.export");
        }

        @Override
        public String getJrxmlFile() {
            return "/reports/GroupList.jrxml";
        }
    },
    RECEIVER_PERSON_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("receiver_person_list.report.title");
        }

        @Override
        public String getExport() {
            return getStringFromResourceBundle("receiver_person_list.report.export");
        }

        @Override
        public String getJrxmlFile() {
            return "/reports/ReceiverPersonList.jrxml";
        }
    };

    public abstract String getTitle();

    public abstract String getExport();

    public abstract String getJrxmlFile();


    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
