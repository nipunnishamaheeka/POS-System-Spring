package lk.ijse.possystembackendspring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
