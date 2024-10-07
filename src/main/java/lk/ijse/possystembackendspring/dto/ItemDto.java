package lk.ijse.possystembackendspring.dto;

import lk.ijse.possystembackendspring.customObj.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto implements SuperDto, ItemResponse {

    private String id;
    private String name;
    private String qty;
    private double price;
}
