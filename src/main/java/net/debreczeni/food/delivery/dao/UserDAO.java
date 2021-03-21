package net.debreczeni.food.delivery.dao;

import net.debreczeni.food.delivery.service.UserService;
import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.User;

import java.util.List;

public class UserDAO {
    private final UserService userService;

    public UserDAO() {
        userService = new UserService();
    }

    public User login(String username, String password) {
        return userService.findByUsernameAndPassword(username, password);
    }

    public List<User> findAll() {
        return userService.findAll();
    }

    public void update(User user) {
        userService.update(user);
    }

    public void delete(User user) {
        userService.delete(user);
    }

    public User register(String name, String username, String password, String nrIdentity, int cnp, String address) throws Exception {
        final User user = new Customer(name, username, password, nrIdentity, cnp, address);
        if (!userService.insert(user)) {
            throw new Exception("Couldn't create user");
        }

        return user;
    }
}
