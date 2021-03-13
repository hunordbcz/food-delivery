package net.debreczeni.food.delivery.bll;

import net.debreczeni.food.delivery.dao.UserDAO;
import net.debreczeni.food.delivery.exceptions.InvalidCredentialsException;
import net.debreczeni.food.delivery.model.User;

public class UserBLL {
    private final UserDAO userDAO;

    public UserBLL() {
        userDAO = new UserDAO();
    }

    public User login(String username, String password) throws InvalidCredentialsException {
        return userDAO.login(username, password);
    }
}
