package lk.ijse.possystembackendspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "order_detail")
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private float id;

    private int qty;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private ItemEntity item;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity order;


}
