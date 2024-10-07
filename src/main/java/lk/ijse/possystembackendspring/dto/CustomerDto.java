package lk.ijse.possystembackendspring.dto;

import lk.ijse.possystembackendspring.customObj.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto implements SuperDto, CustomerResponse {

    private String custId;
    private String custName;
    private String custAddress;
    private double custSalary;
}
