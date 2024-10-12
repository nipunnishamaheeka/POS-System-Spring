package lk.ijse.possystembackendspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity implements SuperEntity {
    @Id
    private String id;
    @CreationTimestamp
    private Timestamp date;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double total;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double discount;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double subTotal;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double cash;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double balance;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetailsEntity> orderDetailsList;
}
