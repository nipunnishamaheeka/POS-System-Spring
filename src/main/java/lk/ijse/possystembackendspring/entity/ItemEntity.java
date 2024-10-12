package lk.ijse.possystembackendspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class ItemEntity implements SuperEntity {
    @Id
    private String id;
    private String name;
    private int qty;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double price;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderDetailsEntity> orderDetailsList;
}
