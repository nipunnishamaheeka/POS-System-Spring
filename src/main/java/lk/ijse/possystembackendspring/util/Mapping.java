package lk.ijse.possystembackendspring.util;

import lk.ijse.possystembackendspring.dto.CustomerDto;
import lk.ijse.possystembackendspring.dto.ItemDto;
import lk.ijse.possystembackendspring.entity.CustomerEntity;
import lk.ijse.possystembackendspring.entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //Customer matters
    public CustomerEntity convertToCustomerEntity(CustomerDto dto){
        return modelMapper.map(dto, CustomerEntity.class);
    }
    public CustomerDto convertToCustomerDto(CustomerEntity entity){
        return modelMapper.map(entity, CustomerDto.class);
    }
    public List<CustomerDto> convertToCustomerDtoList(List<CustomerEntity> entities){
     return modelMapper.map(entities, new TypeToken<List<CustomerDto>>(){}.getType());
    }
    //matters of Item
    public ItemDto convertToItemDTO(ItemEntity item) {
        return modelMapper.map(item, ItemDto.class);
    }
    public ItemEntity convertToItemEntity(ItemDto dto) {
        return modelMapper.map(dto, ItemEntity.class);
    }
    public List<ItemDto> convertToItemDTOList(List<ItemEntity> items) {
        return modelMapper.map(items, new TypeToken<List<ItemDto>>() {}.getType());
    }

}
