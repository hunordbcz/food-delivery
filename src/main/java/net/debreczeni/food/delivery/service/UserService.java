package net.debreczeni.food.delivery.service;

import net.debreczeni.food.delivery.dto.UserDTO;
import net.debreczeni.food.delivery.exceptions.InvalidCredentialsException;
import net.debreczeni.food.delivery.model.Administrator;
import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.User;
import net.debreczeni.food.delivery.util.OrderTypes;
import net.debreczeni.food.delivery.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class UserService extends AbstractService<UserDTO> {

    public UserService() {
        super("users");
    }

    public User login(String username, String password) throws InvalidCredentialsException {
        List<Pair<String, Object>> rules = new LinkedList<>();
        rules.add(new Pair<>("username", username));
        rules.add(new Pair<>("password", password));

        List<UserDTO> response = this.select(rules, OrderTypes.ASC);
        if (response != null && !response.isEmpty()) {
            return fromDTO(response.get(0));
        }

        throw new InvalidCredentialsException();
    }

    private UserDTO toDTO(User user){
        if(user instanceof Administrator){
            Administrator administrator = (Administrator) user;
            return UserDTO.builder()
                    .id(administrator.getId())
                    .name(administrator.getName())
                    .is_admin(true)
                    .username(administrator.getUsername())
                    .password(administrator.getPassword())
                    .build();
        }

        if(user instanceof Customer){
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
                    .build();
        }

        throw new IllegalArgumentException("User type not found");
    }

    private User fromDTO(UserDTO user){
        if(user.getIs_admin()){
            return new Administrator(
                    user.getId(),
                    user.getName(),
                    user.getUsername(),
                    user.getPassword()
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
                user.getIs_loyal()
        );
    }

}
