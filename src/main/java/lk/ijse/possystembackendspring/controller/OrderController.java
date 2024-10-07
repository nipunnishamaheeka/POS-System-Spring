package lk.ijse.possystembackendspring.controller;

import lk.ijse.possystembackendspring.dto.ItemDto;
import lk.ijse.possystembackendspring.dto.OrderDto;
import lk.ijse.possystembackendspring.exception.DataPersistFailedException;
import lk.ijse.possystembackendspring.service.ItemService;
import lk.ijse.possystembackendspring.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;

    //save
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDto orderDto) {

        try {

            orderService.saveOrders(orderDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "allorders", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDto> getAllOrders() {
       // System.out.println("itemService.getAllItem() = " + itemService.getAllItem());
        return orderService.getAllOrders();
    }

}
