package it.unipr.ingegneria.utils;

import it.unipr.ingegneria.entities.notifications.CustomerNotification;
import it.unipr.ingegneria.entities.user.User;

/**
 * The {@code LogMessages} class define the output messages of the system
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */

public class LogMessages {
    /**
     * Method called when the wine is finished
     *
     * @param user     User
     * @param wineName Wine Name
     * @return string Message Description
     */
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

    /**
     * Method called when the authentication of the user is not correct
     *
     * @param user User
     * @return string Message Description
     */
    public static String userNoAuth(User user) {
        StringBuilder builder = new StringBuilder()
                .append("User ")
                .append(user.getName())
                .append(" ")
                .append(user.getSurname())
                .append(" not authenticated");
        return builder.toString();
    }

    /**
     * Method called when a order is well completed
     *
     * @param user
     * @return string Message Description
     */
    public static String userCompletedOrder(User user) {
        StringBuilder builder = new StringBuilder()
                .append("User ")
                .append(user.getName())
                .append(" ")
                .append(user.getSurname())
                .append(" has completed order");
        return builder.toString();
    }

    /**
     * Method callled when an employee supplied the wine
     *
     * @param user User
     * @param name name
     * @return string Message Description
     */
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

    /**
     * Metod call when a user receive a notify
     *
     * @param user         User
     * @param notification Notification
     * @return string
     */
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
