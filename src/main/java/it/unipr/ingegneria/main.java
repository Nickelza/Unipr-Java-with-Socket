package it.unipr.ingegneria;


import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.Employee;
import java.util.List;

/**
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 */

public class main {
    public static void main(String... args) throws Exception {

        /**
         * Instances a new Wine Shop
         */
        WineShop wineShop = new WineShop("ws1");

        /**
         * Adds an employee to the Wine Shop who in turn adds some wines
         */
        Employee emp = new Employee(1L, "Lucaz", "Bianchi", "email", "pwd", wineShop);

        emp.provisionWine("Chianti", 15);
        emp.provisionWine("Lambrusco", 15);
        wineShop.addUser(emp);

        /***
         * Adds a customer to the shop who searches for the wines previously added, both by name and by production year
         */
        Customer customer = new Customer(1L, "Ciccio", "Pasticcio", "email", "pwd", wineShop);
        wineShop.addUser(customer);
        List<Wine> l = customer.findByName("Chianti");
        List<Wine> a = customer.findByYear(2020);

        /**
         * Previously created customer now log's in to the system and performs an order
         */
        customer.login("email", "pwd");
        customer.order("Chianti", 14);

        /***
         * A new customer log's in to the system and performes another order
         */
        Customer customer1 = new Customer(2L, "Mario", "Rossi", "email", "pwd", wineShop);
        wineShop.addUser(customer1);
        customer1.login("email", "pwd");
        customer1.order("Lambrusco", 30);

        /***
         * The orders are processed by an employee
         */
        emp.sendOrders();

    }
}
