package it.unipr.ingegneria.utils;

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
