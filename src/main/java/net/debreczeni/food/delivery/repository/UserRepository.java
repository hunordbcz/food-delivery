package net.debreczeni.food.delivery.repository;

import net.debreczeni.food.delivery.dto.UserDTO;

public class UserRepository extends AbstractRepository<UserDTO> {
    private static final String TABLE_NAME = "users";

    public UserRepository() {
        super(TABLE_NAME);
    }

    @Override
    public Boolean softDelete(UserDTO userDTO) {
        userDTO.setIs_deleted(true);
        return update(userDTO);
    }
}
