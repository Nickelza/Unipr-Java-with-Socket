package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.api.IUserManager;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.repo.RelUserWineshop;
import it.unipr.ingegneria.repo.UserRepo;
import it.unipr.ingegneria.utils.Type;

public class Admin extends User implements IUserManager {

    UserRepo userRepo = UserRepo.getInstance();
    RelUserWineshop relUserWineshop = RelUserWineshop.getInstance();


    public Admin(String name, String surname, String email, String password) {
        super(name, surname, email, password, Type.ADMIN);
        userRepo.add(this);
    }

    @Override
    public void addUser(User user) {
        userRepo.add(user);
        if (user instanceof Employee) {
            Employee employee = (Employee) user;
            WineShop wineShop = employee.getWineshop();
            relUserWineshop.add(user, wineShop);
        }
    }

    @Override
    public void deleteUser(User user) {
        // userRepo.delete(user);
    }

    @Override
    public Boolean hasUser(User item) {
        return null;
    }


}
