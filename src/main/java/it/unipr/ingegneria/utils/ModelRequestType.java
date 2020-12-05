package it.unipr.ingegneria.utils;

/**
 * The {@code ModelRequestType} is a utils enum for define the request type of a given request .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
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
