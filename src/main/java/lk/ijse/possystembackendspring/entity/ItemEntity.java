package lk.ijse.possystembackendspring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class ItemEntity implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String qty;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double price;
}
