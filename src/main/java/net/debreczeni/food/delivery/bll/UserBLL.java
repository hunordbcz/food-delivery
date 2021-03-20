package net.debreczeni.food.delivery.bll;

import net.debreczeni.food.delivery.dao.UserDAO;
import net.debreczeni.food.delivery.exceptions.InvalidCredentialsException;
import net.debreczeni.food.delivery.model.Administrator;
import net.debreczeni.food.delivery.model.User;
import net.debreczeni.food.delivery.presentation.AdminFrame;
import net.debreczeni.food.delivery.presentation.CustomerFrame;

import javax.swing.*;
import java.util.List;
import java.util.function.Function;

public class UserBLL {
    private final UserDAO userDAO;

    public UserBLL() {
        userDAO = new UserDAO();
    }

    public User login(String username, String password) throws InvalidCredentialsException {
        return userDAO.login(username, password);
    }

    public User register(String name, String username, String password, String passwordConfirmation, String nrIdentity, String cnp, String address){
        return userDAO.register(name, username, password, passwordConfirmation, nrIdentity, cnp, address);
    }

    public Function<JFrame, JFrame> getHomepage(User user) {
        if (user instanceof Administrator) {
            return AdminFrame::new;
        } else {
            return CustomerFrame::new;
        }
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public void update(User user) {
        userDAO.update(user);
    }

    public void delete(User user) {
        userDAO.delete(user);
    }
}
