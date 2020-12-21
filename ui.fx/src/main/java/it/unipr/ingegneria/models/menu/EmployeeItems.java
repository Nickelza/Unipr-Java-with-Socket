package it.unipr.ingegneria.models.menu;

public enum EmployeeItems {
    PROFILE {
        @Override
        public String toString() {
            return "Profile";
        }
    },
    PROVISIONING_WINE {
        @Override
        public String toString() {
            return "Provisioning Wine";
        }
    },
    SEND_ORDERS {
        @Override
        public String toString() {
            return "Send order";
        }
    },


}
