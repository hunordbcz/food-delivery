package net.debreczeni.food.delivery.service;

import net.debreczeni.food.delivery.dto.ItemDTO;
import net.debreczeni.food.delivery.model.Item;
import net.debreczeni.food.delivery.repository.ItemRepository;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ItemService {
    private ItemRepository itemRepository = new ItemRepository();

    public ItemService() {
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

    public List<Item> getAll() {
        return itemRepository.findAll().stream().map(ItemService::fromDTO).collect(Collectors.toList());
    }

    public void update(Item item) {
        itemRepository.update(toDTO(item));
    }

    public void insert(Item item) {
        itemRepository.insert(toDTO(item));
    }

    public void delete(Item item) {
        itemRepository.delete(toDTO(item));
    }

    private static class Singleton {
        private static final ItemService INSTANCE = new ItemService();
    }
}
