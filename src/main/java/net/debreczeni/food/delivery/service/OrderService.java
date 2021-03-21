package net.debreczeni.food.delivery.service;

import net.debreczeni.food.delivery.dto.OrderDTO;
import net.debreczeni.food.delivery.model.Item;
import net.debreczeni.food.delivery.model.Order;
import net.debreczeni.food.delivery.model.PaymentType;
import net.debreczeni.food.delivery.model.User;
import net.debreczeni.food.delivery.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService implements Service<Order>{
    private final OrderRepository orderRepository = new OrderRepository();
    private static final ItemService itemService = new ItemService();

    public OrderService() {
    }

    public static OrderService getInstance() {
        return Singleton.INSTANCE;
    }

    public static OrderDTO toDTO(Order cart) {
        return OrderDTO.builder()
                .id(cart.getUser().getId())
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
                .is_deleted(cart.getIsDeleted())
                .build();
    }

    public static Order fromDTO(OrderDTO orderDTO) {
        final List<Item> orderItems = Arrays.stream(orderDTO
                .getItems_list().split(","))
                .mapToInt(Integer::parseInt).mapToObj(itemService::findByID)
                .collect(Collectors.toList());

        return new Order(
                orderDTO.getId(),
                UserService.getInstance().findByID(orderDTO.getUser_id()),
                orderItems,
                orderDTO.getDelivery_address(),
                PaymentType.getType(orderDTO.getPayment_type()),
                orderDTO.getIs_processed(),
                orderDTO.getCreated_at(),
                orderDTO.getIs_deleted()
        );
    }

    @Override
    public List<Order> findAll() {
        return findAll(false);
    }

    @Override
    public List<Order> findAll(boolean showDeleted) {
        if (showDeleted) {
            return orderRepository.findAll().stream().map(OrderService::fromDTO).collect(Collectors.toList());
        } else {
            return orderRepository.findAll().stream().map(OrderService::fromDTO)
                    .filter(order -> !order.getIsDeleted()).collect(Collectors.toList());
        }
    }

    @Override
    public boolean insert(Order order) {
        return orderRepository.insert(toDTO(order));
    }

    @Override
    public Order findByID(int id) {
        return null;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public boolean delete(Order order) {
        return false;
    }

    @Override
    public boolean softDelete(Order order) {
        return false;
    }

    private static class Singleton {
        private static final OrderService INSTANCE = new OrderService();
    }
}