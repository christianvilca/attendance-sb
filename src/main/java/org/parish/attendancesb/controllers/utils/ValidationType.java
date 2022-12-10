package org.parish.attendancesb.controllers.utils;

public enum ValidationType {

    TEXT {
        @Override
        public String getPattern() {
            return "[a-zA-ZÀ-ÿ ]+";
        }
    },
    NUMBER {
        @Override
        public String getPattern() {
            return "[0-9]+";
        }
    },
    ALPHANUMERIC {
        @Override
        public String getPattern() {
            return "[a-zA-ZÀ-ÿ0-9 ]+";
        }
    },
    EMAIL {
        @Override
        public String getPattern() {
            return "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+";
        }
    };

    public abstract String getPattern();
}
