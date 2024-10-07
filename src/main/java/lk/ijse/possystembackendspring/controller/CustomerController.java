package lk.ijse.possystembackendspring.controller;

import lk.ijse.possystembackendspring.customObj.CustomerResponse;
import lk.ijse.possystembackendspring.dto.CustomerDto;
import lk.ijse.possystembackendspring.exception.CustomerNotFoundException;
import lk.ijse.possystembackendspring.exception.DataPersistFailedException;
import lk.ijse.possystembackendspring.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor

public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    //save
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDto customerDto){

                try{

                    customerService.saveCustomer(customerDto);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }catch (DataPersistFailedException e){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }catch (Exception e){
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable ("id") String customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse getSelectedCustomer(@PathVariable ("id") String customerId){
        return customerService.getSelectedCustomer(customerId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getAllCustomer(){
        System.out.println();
        return customerService.getAllCustomer();
    }
    @PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") String customerId, @RequestBody CustomerDto customerDto){
        try {
            customerDto.setId(customerId);
            customerService.updateCustomer(customerDto);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
