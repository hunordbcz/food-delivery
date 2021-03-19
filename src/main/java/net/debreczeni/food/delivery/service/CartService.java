package net.debreczeni.food.delivery.service;

import net.debreczeni.food.delivery.dto.CartDTO;
import net.debreczeni.food.delivery.model.Cart;
import net.debreczeni.food.delivery.model.PaymentType;

import java.util.stream.Collectors;

public class CartService {
    private final UserService userService = UserService.getInstance();

    private CartService() {
    }

    public static CartService getInstance() {
        return Singleton.INSTANCE;
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
                .payment_type(cart.getPaymentType().getCode())
                .build();
    }

    public static Cart fromDTO(CartDTO cartDTO) {
        return new Cart(
                cartDTO.getId(),
                UserService.getInstance().findByID(cartDTO.getUser_id()),
                cartDTO.getDelivery_address(),
                PaymentType.getType(cartDTO.getPayment_type()),
                cartDTO.getIs_processed(),
                cartDTO.getCreated_at()
        );
    }

    private static class Singleton {
        private static final CartService INSTANCE = new CartService();
    }
}
