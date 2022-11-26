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
    USER_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("user_list.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/UserList.fxml";
        }
    },
    USER {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("user.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/User.fxml";
        }
    },
    CATEQUISTA_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("catequista_list.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/CatequistaList.fxml";
        }
    },
    CATEQUISTA {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("catequista.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/Catequista.fxml";
        }
    },
    CATEQUISTA_SEARCH {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("catequista_search.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/CatequistaSearch.fxml";
        }
    },
    CATEQUESIS_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("catequesis_list.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/CatequesisList.fxml";
        }
    },
    CATEQUESIS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("catequesis.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/Catequesis.fxml";
        }
    },
    CATEQUESIS_SEARCH {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("catequesis_search.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/CatequesisSearch.fxml";
        }
    },
    CATEQUESIS_CATEQUISTA_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("catequesis_catequista_search.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/CatequesisCatequistaList.fxml";
        }
    },
    GROUP_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("group_list.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/GroupList.fxml";
        }
    },
    GROUP {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("group.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/Group.fxml";
        }
    },
    GROUP_CATEQUISTA_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("group_catequista_search.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/GroupCatequistaList.fxml";
        }
    },
    RECEIVER_PERSON_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("receiver_person_list.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/ReceiverPersonList.fxml";
        }
    },
    RECEIVER_PEOPLE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("receiver_person.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/ReceiverPerson.fxml";
        }
    },
    ATTENDANCE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("attendance.title");
        }

        @Override
        public String getFxmlFile() {
            return "/view/Attendance.fxml";
        }
    },
    ATTENDANCE_REPORT {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("attendance.report.title");
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