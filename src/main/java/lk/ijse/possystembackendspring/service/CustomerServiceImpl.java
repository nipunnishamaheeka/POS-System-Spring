package lk.ijse.possystembackendspring.service;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendspring.customObj.CustomerResponse;
import lk.ijse.possystembackendspring.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void saveCustomer(CustomerDto customerDto) {

    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {

    }

    @Override
    public void deleteCustomer(String customerId) {

    }

    @Override
    public CustomerResponse getSelectedCustomer(String customerId) {
        return null;
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        return List.of();
    }
}
