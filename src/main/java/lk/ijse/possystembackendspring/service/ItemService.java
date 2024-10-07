package lk.ijse.possystembackendspring.service;

import lk.ijse.possystembackendspring.customObj.CustomerResponse;
import lk.ijse.possystembackendspring.customObj.ItemResponse;
import lk.ijse.possystembackendspring.dto.ItemDto;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDto itemDto);
    void updateItem(ItemDto itemDto);
    void deleteItem(String itemDto);
    ItemResponse getSelectedItem(String itemId);
    List<ItemDto> getAllItem();
}
