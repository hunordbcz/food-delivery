package net.debreczeni.food.delivery.service;

import net.debreczeni.food.delivery.repository.ItemRepository;
import net.debreczeni.food.delivery.dto.ItemDTO;
import net.debreczeni.food.delivery.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemService implements Service<Item> {
    private final ItemRepository itemRepository = new ItemRepository();

    public ItemService() {
    }

    public static ItemDTO toDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .is_deleted(item.getIsDeleted())
                .build();
    }

    public static Item fromDTO(ItemDTO itemDTO) {
        return new Item(
                itemDTO.getId(),
                itemDTO.getName(),
                itemDTO.getPrice(),
                itemDTO.getIs_deleted()
        );
    }

    @Override
    public List<Item> findAll() {
        return findAll(false);
    }

    @Override
    public List<Item> findAll(boolean showDeleted) {
        if (showDeleted) {
            return itemRepository.findAll().stream().map(ItemService::fromDTO).collect(Collectors.toList());
        } else {
            return itemRepository.findAll().stream().map(ItemService::fromDTO)
                    .filter(item -> !item.getIsDeleted()).collect(Collectors.toList());
        }
    }

    @Override
    public boolean update(Item item) {
        return itemRepository.update(toDTO(item));
    }

    @Override
    public boolean insert(Item item) {
        return itemRepository.insert(toDTO(item));
    }

    @Override
    public boolean delete(Item item) {
        return delete(item, true);
    }

    public boolean delete(Item item, boolean softDelete) {
        return softDelete ? softDelete(item) : itemRepository.delete(toDTO(item));
    }

    @Override
    public boolean softDelete(Item item) {
        return itemRepository.softDelete(toDTO(item));
    }

    @Override
    public Item findByID(int id) {
        return fromDTO(itemRepository.findByID(id));
    }
}
