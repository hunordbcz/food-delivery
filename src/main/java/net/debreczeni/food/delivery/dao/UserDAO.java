package net.debreczeni.food.delivery.dao;

import net.debreczeni.food.delivery.exceptions.InvalidCredentialsException;
import net.debreczeni.food.delivery.model.User;
import net.debreczeni.food.delivery.service.UserService;

public class UserDAO {
    private final UserService userService;

    public UserDAO() {
        userService = new UserService();
    }

    public User login(String username, String password) throws InvalidCredentialsException {
        return userService.login(username, password);
    }
}
