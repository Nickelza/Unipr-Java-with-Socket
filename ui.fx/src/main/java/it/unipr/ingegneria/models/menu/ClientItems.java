package it.unipr.ingegneria.models.menu;

/**
 * The {@code ClientItems} is a Enum that contains the items of the {@code ClientMenu}
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public enum ClientItems {
    PROFILE {
        @Override
        public String toString() {
            return "Profile";
        }
    },
    ORDER_WINE {
        @Override
        public String toString() {
            return "Order Wine";
        }
    },
    SEARCH_WINE_BY_NAME {
        @Override
        public String toString() {
            return "Wine by name";
        }
    },
    SEARCH_WINE_BY_YEAR {
        @Override
        public String toString() {
            return "Wine by year";
        }
    },
}
