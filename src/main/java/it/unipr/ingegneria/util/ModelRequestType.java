package it.unipr.ingegneria.util;

public enum ModelRequestType {
    LOGIN {
        @Override
        public String toString() {
            return "UserLoginRequest";
        }
    },
    CREATE_USER {
        @Override
        public String toString() {
            return "CreateUserRequest";
        }
    },
    SEARCH {
        @Override
        public String toString() {
            return "SearchRequest";
        }
    },
    PROVISIONING {
        @Override
        public String toString() {
            return "CreateProvisioningRequest";
        }
    },
    ORDER {
        @Override
        public String toString() {
            return "CreateOrderRequest";
        }
    }
}
