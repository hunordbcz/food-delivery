package net.debreczeni.food.delivery.dao;

import net.debreczeni.food.delivery.exceptions.InvalidCredentialsException;
import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.User;
import net.debreczeni.food.delivery.service.UserService;

import java.util.List;

public class UserDAO {
    private final UserService userService;

    public UserDAO() {
        userService = new UserService();
    }

    public User login(String username, String password) throws InvalidCredentialsException {
        final User user = userService.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        return user;
    }

    public User register(String name, String username, String password, String passwordConfirmation, String nrIdentity, String cnp, String address) {
        final User user = new Customer(name, username, password, nrIdentity, Integer.parseInt(cnp), address);
        if (!userService.insert(user)) {

        }

        return user;
    }

    public List<User> getAll() {
        return userService.getAll();
    }

    public void update(User user) {
        userService.update(user);
    }

    public void delete(User user) {
        userService.delete(user);
    }
}
