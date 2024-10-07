package lk.ijse.possystembackendspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity implements SuperEntity {
@Id
    private String id;
@Column(columnDefinition = "DATE")
    private String date;
    private String customerId;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double total;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private String discount;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double subTotal;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double cash;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double balance;
}
