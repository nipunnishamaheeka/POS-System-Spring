package lk.ijse.possystembackendspring.service;


import lk.ijse.possystembackendspring.customObj.CustomerResponse;
import lk.ijse.possystembackendspring.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);
    void updateCustomer(CustomerDto customerDto);
    void deleteCustomer(String customerId) throws ClassNotFoundException;
    CustomerResponse getSelectedCustomer(String customerId);
    List<CustomerDto> getAllCustomer();
}
