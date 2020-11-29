package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.api.IUserManager;
import it.unipr.ingegneria.api.Persistable;
import it.unipr.ingegneria.db.persistance.relations.RelUserWineshop;
import it.unipr.ingegneria.db.persistance.UserDAO;

public class Admin extends User implements IUserManager, Persistable<Admin> {

    private transient UserDAO userDAO = UserDAO.getInstance();
    private transient RelUserWineshop relUserWineshop = RelUserWineshop.getInstance();


    /**
     * Constructor used when instantiate the object without knowing any data. Will be enriched later
     */
    public Admin() {
        super();
    }


    @Override
    public void addUser(User user) {
        wineshop.addUser(user);
    }

    @Override
    public void deleteUser(User user) {
        // userRepo.delete(user);
    }

    @Override
    public User findByMailAndPassword(String email, String password) {
        return wineshop.findByMailAndPassword(email, password);
    }


    @Override
    public Admin persist() {
        userDAO.add(this);
        relUserWineshop.add(this, this.wineshop);
        return this;
    }
}
