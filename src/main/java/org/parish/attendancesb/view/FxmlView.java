package org.parish.attendancesb.view;

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
    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/Login.fxml";
        }
    },
    USER {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/User.fxml";
        }
    },
    CATEQUESIS_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/CatequesisList.fxml";
        }
    },
    CATEQUESIS_SEARCH {
        @Override
        public String getTitle() {
            return ""; //getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/CatequesisSearch.fxml";
        }
    },
    CATEQUESIS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/Catequesis.fxml";
        }
    },
    COORDINATOR_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/CoordinatorList.fxml";
        }
    },
    COORDINATOR {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/Coordinator.fxml";
        }
    },
    GROUP_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/GroupList.fxml";
        }
    },
    GROUP {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/Group.fxml";
        }
    },
    CATEQUISTA_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/CatequistaList.fxml";
        }
    },
    CATEQUISTA {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/Catequista.fxml";
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
    RECEIVER_PEOPLE_SEARCH {
        @Override
        public String getTitle() {
            return ""; //getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/ReceiverPersonSearch.fxml";
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
    },
    ATTENDANCE {
        @Override
        public String getTitle() {
            return ""; //getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/Attendance.fxml";
        }
    },
    ATTENDANCE_REPORT {
        @Override
        public String getTitle() {
            return ""; //getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/AttendanceReport.fxml";
        }
    };

    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}