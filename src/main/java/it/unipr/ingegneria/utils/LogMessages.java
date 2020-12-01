package it.unipr.ingegneria.utils;

import it.unipr.ingegneria.entities.notifications.CustomerNotification;
import it.unipr.ingegneria.entities.user.User;

public class LogMessages {
    public static String wineEnded(User user, String wineName) {
        StringBuilder builder = new StringBuilder()
                .append("Dear ")
                .append(user.getName())
                .append(" ")
                .append(user.getSurname())
                .append(" the wine that you searched ")
                .append(wineName)
                .append(" at the moment is not available");
        return builder.toString();
    }

    public static String userNoAuth(User user) {
        StringBuilder builder = new StringBuilder()
                .append("User ")
                .append(user.getName())
                .append(" ")
                .append(user.getSurname())
                .append(" not authenticated");
        return builder.toString();
    }

    public static String userCompletedOrder(User user) {
        StringBuilder builder = new StringBuilder()
                .append("User ")
                .append(user.getName())
                .append(" ")
                .append(user.getSurname())
                .append(" has completed order");
        return builder.toString();
    }

    public static String employeeProvisiongWine(User user, String name) {
        StringBuilder builder = new StringBuilder()
                .append("Employee ")
                .append(user.getName())
                .append(" ")
                .append(user.getSurname())
                .append(" is provisiong wine")
                .append(" ")
                .append(name);
        return builder.toString();
    }

    public static String userNotification(User user, CustomerNotification notification) {
        StringBuilder builder = new StringBuilder()
                .append("Dear ")
                .append(user.getName())
                .append(" ")
                .append(user.getSurname())
                .append(" the wine that you searched ")
                .append(notification.getWineName())
                .append(" is now is available");
        return builder.toString();
    }
}
