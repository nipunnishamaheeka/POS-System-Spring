package lk.ijse.possystembackendspring.dto;

import lk.ijse.possystembackendspring.customObj.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto implements SuperDto, OrderResponse {
    private String id;
    private Timestamp date;
    private String customerId;
    private List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
    private double total;
    private double discount;
    private double subTotal;
    private double cash;
    private double balance;
}
