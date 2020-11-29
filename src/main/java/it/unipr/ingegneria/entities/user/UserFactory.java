package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.utils.Type;

public class UserFactory {
    public static User getUser(Type type) {
        User user = null;
        switch (type) {
            case ADMIN:
                user = new Admin().setUserType(Type.ADMIN);
                break;
            case CLIENT:
                user = new Customer().setUserType(Type.CLIENT);
                break;
            case EMPLOYEE:
                user = new Employee().setUserType(Type.EMPLOYEE);
                break;
            default:
                user = null;
                break;
        }
        return user;
    }
}
