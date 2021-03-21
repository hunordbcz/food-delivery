package net.debreczeni.food.delivery.bll;

import net.debreczeni.food.delivery.dao.UserDAO;
import net.debreczeni.food.delivery.exceptions.InvalidCredentialsException;
import net.debreczeni.food.delivery.exceptions.InvalidInput;
import net.debreczeni.food.delivery.model.Administrator;
import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.User;
import net.debreczeni.food.delivery.presentation.AdminFrame;
import net.debreczeni.food.delivery.presentation.CustomerFrame;

import javax.swing.*;
import java.util.List;
import java.util.function.BiFunction;

public class UserBLL {
    private final UserDAO userDAO;

    public UserBLL() {
        userDAO = new UserDAO();
    }

    public User login(String username, String password) throws InvalidCredentialsException {
        final User user = userDAO.login(username, password);
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (user.getIsDeleted()) {
            throw new InvalidCredentialsException("Your account has been deleted");
        }

        return user;
    }

    public User register(String name, String username, String password, String passwordConfirmation, String nrIdentity, String cnp, String address) throws Exception {
        if (!password.equals(passwordConfirmation)) {
            throw new InvalidInput("Passwords don't match");
        }
        if (name.isEmpty() ||
                username.isEmpty() ||
                nrIdentity.isEmpty() ||
                cnp.isEmpty() ||
                address.isEmpty()) {
            throw new InvalidInput("Some fields are empty");
        }

        int cnpVal;
        try {
            cnpVal = Integer.parseInt(cnp);
        } catch (Exception ex) {
            throw new InvalidInput("Invalid CNP");
        }

        return userDAO.register(name, username, password, nrIdentity, cnpVal, address);
    }

    public BiFunction<JFrame, User, JFrame> getHomepage(User user) {
        if (user instanceof Administrator) {
            return AdminFrame::new;
        } else {
            return CustomerFrame::new;
        }
    }

    public List<User> getAll() {
        return userDAO.findAll();
    }

    public void update(User user) {
        userDAO.update(user);
    }

    public void delete(User user) {
        userDAO.delete(user);
    }

    public void updateInformation(Customer customer, String name, String username, String password, String passwordConfirmation, String nrIdentity, String cnp, String address) throws InvalidInput {
        if (!password.equals(passwordConfirmation)) {
            throw new InvalidInput("Password doesn't match");
        }

        int cnpInt;
        try {
            cnpInt = Integer.parseInt(cnp);
        } catch (Exception e) {
            throw new InvalidInput("Invalid CNP");
        }

        if (name.isEmpty() ||
                username.isEmpty() ||
                password.isEmpty() ||
                nrIdentity.isEmpty() ||
                address.isEmpty()) {
            throw new InvalidInput("Some fields are empty");
        }

        customer.setUsername(username);
        customer.setPassword(password);
        customer.setNrIdentity(nrIdentity);
        customer.setCnp(cnpInt);
        customer.setAddress(address);

        userDAO.update(customer);
    }
}
