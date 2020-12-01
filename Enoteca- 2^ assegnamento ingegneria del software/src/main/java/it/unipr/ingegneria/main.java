package it.unipr.ingegneria;


import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.Employee;
import java.util.List;

/**
 * Is the main class of the system
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class main {
    public static void main(String... args) throws Exception {


        WineShop wineShop = new WineShop();

        Employee emp = new Employee(1L, "Luca", "Bianchi", "email", "pwd", wineShop);
        emp.provisionWine("Chianti", 15);
        emp.provisionWine("Lambrusco", 15);
        wineShop.addUser(emp);

        Customer customer = new Customer(1L, "Ciccio", "Pasticcio", "email", "pwd", wineShop);
        wineShop.addUser(customer);
        
        customer.login("email", "pwd");
        customer.order("Chianti", 14);
        
        List<Wine> l = customer.findByName("Chianti");
        List<Wine> a = customer.findByYear(2020);




        Customer customer1 = new Customer(2L, "Mario", "Rossi", "email", "pwd", wineShop);
        wineShop.addUser(customer1);
        customer1.login("email", "pwd");
        customer1.order("Lambrusco", 30);

        emp.provisionWine("Lambrusco", 15);
        customer1.order("Lambrusco", 30);

        emp.sendOrders();

    }
}
