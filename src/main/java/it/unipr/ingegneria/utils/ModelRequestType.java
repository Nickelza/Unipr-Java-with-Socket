package it.unipr.ingegneria.utils;

public enum ModelRequestType {
    LOGIN {
        @Override
        public String toString() {
            return "UserLoginRequest";
        }
    },
    LOGOUT {
        @Override
        public String toString() {
            return "UserLogoutRequest";
        }
    },
    CREATE {
        @Override
        public String toString() {
            return "CreateRequest";
        }
    },

    SEARCH {
        @Override
        public String toString() {
            return "SearchRequest";
        }
    },

}
