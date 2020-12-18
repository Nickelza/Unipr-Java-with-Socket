package it.unipr.ingegneria.models.menu;

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
    LOGOUT {
        @Override
        public String toString() {
            return "Logout";
        }
    },

}
