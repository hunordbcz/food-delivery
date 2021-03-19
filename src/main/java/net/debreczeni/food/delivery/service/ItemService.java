package net.debreczeni.food.delivery.service;

import net.debreczeni.food.delivery.dto.ItemDTO;
import net.debreczeni.food.delivery.model.Item;

public class ItemService {
    private ItemService() {
    }

    public static ItemService getInstance() {
        return Singleton.INSTANCE;
    }

    public static ItemDTO toDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .build();
    }

    public static Item fromDTO(ItemDTO itemDTO) {
        return new Item(
                itemDTO.getId(),
                itemDTO.getName(),
                itemDTO.getPrice()
        );
    }

    private static class Singleton {
        private static final ItemService INSTANCE = new ItemService();
    }
}
