package org.parish.attendancesb.config;

import java.util.ResourceBundle;

public enum FxmlView {

    MAIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/Sidebar.fxml";
        }
    },
    RECEIVER_PEOPLE_LIST {
        @Override
        public String getTitle() {
            return ""; //getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/ReceiverPersonList.fxml";
        }
    },
    RECEIVER_PEOPLE {
        @Override
        public String getTitle() {
            return ""; //getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/ReceiverPerson.fxml";
        }
    };

    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}