package net.debreczeni.food.delivery.service;

import net.debreczeni.food.delivery.dto.UserDTO;
import net.debreczeni.food.delivery.model.Administrator;
import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.User;
import net.debreczeni.food.delivery.repository.SQL;
import net.debreczeni.food.delivery.repository.UserRepository;
import net.debreczeni.food.delivery.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService implements Service<User> {
    private final UserRepository userRepository = new UserRepository();

    public static UserService getInstance() {
        return Singleton.INSTANCE;
    }

    public User findByID(int id) {
        return fromDTO(userRepository.findByID(id));
    }

    @Override
    public List<User> findAll() {
        return findAll(false);
    }

    @Override
    public List<User> findAll(boolean showDeleted) {
        if (showDeleted) {
            return userRepository.findAll().stream().map(this::fromDTO).collect(Collectors.toList());
        } else {
            return userRepository.findAll().stream().map(this::fromDTO)
                    .filter(user -> !user.getIsDeleted()).collect(Collectors.toList());
        }
    }

    public User findByUsernameAndPassword(String username, String password) {
        final List<Pair<String, Object>> rules = new LinkedList<>();
        rules.add(new Pair<>("username", username));
        rules.add(new Pair<>("password", password));

        final List<UserDTO> response = userRepository.select(rules, SQL.ORDER_TYPE.ASC);
        if (response != null && !response.isEmpty()) {
            return fromDTO(response.get(0));
        }

        return null;
    }

    @Override
    public boolean insert(User user) {
        return userRepository.insert(toDTO(user));
    }

    private UserDTO toDTO(User user) {
        if (user instanceof Administrator) {
            Administrator administrator = (Administrator) user;
            return UserDTO.builder()
                    .id(administrator.getId())
                    .name(administrator.getName())
                    .is_admin(true)
                    .username(administrator.getUsername())
                    .password(administrator.getPassword())
                    .is_deleted(administrator.getIsDeleted())
                    .build();
        }

        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            return UserDTO.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .nr_identity(customer.getNrIdentity())
                    .cnp(customer.getCnp())
                    .address(customer.getAddress())
                    .is_admin(false)
                    .is_loyal(customer.getIsLoyal())
                    .username(customer.getUsername())
                    .password(customer.getPassword())
                    .is_deleted(customer.getIsDeleted())
                    .build();
        }

        throw new IllegalArgumentException("User type not found");
    }

    private User fromDTO(UserDTO user) {
        if (user.getIs_admin()) {
            return new Administrator(
                    user.getId(),
                    user.getName(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getIs_deleted()
            );
        }

        return new Customer(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getNr_identity(),
                user.getCnp(),
                user.getAddress(),
                user.getIs_loyal(),
                user.getIs_deleted()
        );
    }

    @Override
    public boolean update(User user) {
        if (user.getId() == null) {
            throw new UnsupportedOperationException("Can't update object without primary key");
        }
        return userRepository.update(toDTO(user));
    }

    @Override
    public boolean delete(User user) {
        return delete(user, true);
    }

    public boolean delete(User user, boolean softDelete){
        return softDelete ? softDelete(user) : userRepository.delete(toDTO(user));
    }

    @Override
    public boolean softDelete(User user) {
        return userRepository.softDelete(toDTO(user));
    }

    private static class Singleton {
        private static final UserService INSTANCE = new UserService();
    }

}
