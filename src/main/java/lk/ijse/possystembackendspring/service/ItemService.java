package lk.ijse.possystembackendspring.service;

import lk.ijse.possystembackendspring.customObj.ItemResponse;
import lk.ijse.possystembackendspring.dto.ItemDto;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDto itemDto);
    void updateItem(ItemDto itemDto);
    void deleteItem(String itemId);
    ItemResponse getSelectedItem(String itemId);
    List<ItemDto> getAllItem();
}
