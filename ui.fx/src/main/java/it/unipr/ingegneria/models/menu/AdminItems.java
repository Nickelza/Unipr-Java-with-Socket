package it.unipr.ingegneria.models.menu;

/**
 * The {@code AdminItems} is a Enum that contains the items of the {@code AdminMenu}
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public enum AdminItems {
    PROFILE {
        @Override
        public String toString() {
            return "Profile";
        }
    },
    INSERT_CLIENT {
        @Override
        public String toString() {
            return "Insert client";
        }
    },
    INSERT_EMPLOYEE {
        @Override
        public String toString() {
            return "Insert employee";
        }
    },
    VIEW_CLIENT {
        @Override
        public String toString() {
            return "List client";
        }
    },
    VIEW_EMPLOYEE {
        @Override
        public String toString() {
            return "List employee";
        }
    },
    INSERT_VINEYARD {
        @Override
        public String toString() {
            return "Insert vineyard";
        }
    },
    VIEW_VINEYARD {
        @Override
        public String toString() {
            return "View vineyard";
        }
    },
    VIEW_ORDER {
        @Override
        public String toString() {
            return "View order";
        }
    },
    VIEW_WINE {
        @Override
        public String toString() {
            return "View wine";
        }
    },


}
