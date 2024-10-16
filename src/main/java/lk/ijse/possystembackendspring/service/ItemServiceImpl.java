package lk.ijse.possystembackendspring.service;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendspring.customObj.ItemErrorResponse;
import lk.ijse.possystembackendspring.customObj.ItemResponse;
import lk.ijse.possystembackendspring.dao.ItemDAO;
import lk.ijse.possystembackendspring.dto.ItemDto;
import lk.ijse.possystembackendspring.entity.ItemEntity;
import lk.ijse.possystembackendspring.exception.CustomerNotFoundException;
import lk.ijse.possystembackendspring.exception.DataPersistFailedException;
import lk.ijse.possystembackendspring.exception.ItemNotFoundException;
import lk.ijse.possystembackendspring.util.AppUtil;
import lk.ijse.possystembackendspring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemDAO itemDAO;
    @Autowired
    private final Mapping mapping;

    @Override
    public void saveItem(ItemDto itemDto) {
        itemDto.setId(AppUtil.createItemId());
        var itemEntity = mapping.convertToItemEntity(itemDto);
        System.out.println("itemEntity = " + itemEntity);
        var savedItem = itemDAO.save(itemEntity);
        System.out.println("savedItem = " + savedItem);
        if(savedItem == null){
            throw new DataPersistFailedException("Cannot save");
        }
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        Optional<ItemEntity> tmpItem = itemDAO.findById(itemDto.getId());
        System.out.println("tmpItem = " + tmpItem);
        if(!tmpItem.isPresent()){
            throw new ItemNotFoundException("Item not found");
        }else {
            tmpItem.get().setName(itemDto.getName());
            tmpItem.get().setPrice(itemDto.getPrice());
            tmpItem.get().setQty(itemDto.getQty());
        }
    }

    @Override
    public void deleteItem(String itemId) {
        Optional<ItemEntity> findId = itemDAO.findById(itemId);
        if(!findId.isPresent()){
            throw new ItemNotFoundException("Item not found");
        }else {
            itemDAO.deleteById(itemId);
        }
    }

    @Override
    public ItemResponse getSelectedItem(String itemId) {
        if(itemDAO.existsById(itemId)){
            return mapping.convertToItemDTO(itemDAO.getReferenceById(itemId));
        }else {
            return new ItemErrorResponse(0,"Item not found");
        }
    }

    @Override
    public List<ItemDto> getAllItem() {

        return mapping.convertToItemDTOList(itemDAO.findAll());

    }
}
