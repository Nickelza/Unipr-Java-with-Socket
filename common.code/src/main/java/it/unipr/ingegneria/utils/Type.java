package it.unipr.ingegneria.utils;

/**
 * The {@code Type} is a utils enum for define the user type .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public enum Type {
    CLIENT {
        @Override
        public String toString() {
            return "CLIENT";
        }
    },
    EMPLOYEE {
        @Override
        public String toString() {
            return "EMPLOYEE";
        }
    },
    ADMIN {
        @Override
        public String toString() {
            return "ADMIN";
        }
    }


}
