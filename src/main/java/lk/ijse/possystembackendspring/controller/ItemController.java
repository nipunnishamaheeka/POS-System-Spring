package lk.ijse.possystembackendspring.controller;
import lk.ijse.possystembackendspring.customObj.ItemResponse;
import lk.ijse.possystembackendspring.dto.ItemDto;
import lk.ijse.possystembackendspring.exception.CustomerNotFoundException;
import lk.ijse.possystembackendspring.exception.DataPersistFailedException;
import lk.ijse.possystembackendspring.exception.ItemNotFoundException;
import lk.ijse.possystembackendspring.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
@RequiredArgsConstructor

public class ItemController {
    @Autowired
    private final ItemService itemService;

    //save
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDto itemDto) {

        try {
            itemService.saveItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getAllItem() {
        System.out.println("itemService.getAllItem() = " + itemService.getAllItem());
        return itemService.getAllItem();
    }

    @GetMapping(value = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemResponse getSelectedItem(@PathVariable("itemId") String itemId) {
        {
            return itemService.getSelectedItem(itemId);
        }

    }

    @PatchMapping(value = "/{itemId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@PathVariable ("itemId") String itemId, @RequestBody ItemDto itemDto) {
//        try {
//            if (item == null && (itemId == null || item.equals(""))){
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//            itemService.updateItem(itemId, item);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }catch (ItemNotFoundException e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        try {
            itemDto.setId(itemId);
            itemService.updateItem(itemDto);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping(value ="/{itemId}" )
    public ResponseEntity<Void> deleteItem(@PathVariable ("itemId") String itemId) {
        try {
            itemService.deleteItem(itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

