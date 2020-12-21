package it.unipr.ingegneria.models.menu;

/**
 * The {@code EmployeeIntems} is a Enum that contains the items of the {@code EmployeeMenu}
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
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
