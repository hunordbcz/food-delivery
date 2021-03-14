package net.debreczeni.food.delivery.service;

import net.debreczeni.food.delivery.dto.CartDTO;
import net.debreczeni.food.delivery.model.Cart;

import java.util.stream.Collectors;

public class CartService extends AbstractService<CartDTO> {
    private final UserService userService = new UserService();

    public CartService() {
        super("shopping_cart");
    }

    public static CartDTO toDTO(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .items_list(cart
                        .getItems().stream()
                        .map(item -> item.getId().toString())
                        .collect(Collectors.joining(","))
                )
                .user_id(cart.getUser().getId())
                .created_at(cart.getCreatedAt())
                .delivery_address(cart.getDeliveryAddress())
                .is_processed(cart.getIsProcessed())
                .payment_type(cart.getPaymentType())
                .build();
    }

    public static Cart fromDTO(CartDTO cartDTO) {
        return new Cart(
                cartDTO.getId(),
                new UserService().findBy
        );
    }
}
