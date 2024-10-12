package lk.ijse.possystembackendspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class CustomerEntity implements SuperEntity {
    @Id
    private String id;
    private String name;
    @Column(name = "address")
    private String address;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double salary;
    @OneToMany
    @JoinColumn(name = "customerId")
    private List<OrderEntity> orderList;
}
