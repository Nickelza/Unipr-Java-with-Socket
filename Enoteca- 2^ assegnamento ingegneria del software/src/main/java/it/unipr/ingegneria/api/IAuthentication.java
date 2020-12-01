package it.unipr.ingegneria.api;

/**
 * The  {@code IAuthentication} interface include the method for the User authentication.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public interface IAuthentication {
    /**
     * Method to execute the login
     *
     * @param c1 Email
     * @param c2 Password
     * @throws Exception
     */
    void login(String c1, String c2) throws Exception;

    /**
     * Method to execute the logout
     *
     * @throws Exception
     */
    void logout() throws Exception;
}