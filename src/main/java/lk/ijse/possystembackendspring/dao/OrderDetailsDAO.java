package lk.ijse.possystembackendspring.dao;

import lk.ijse.possystembackendspring.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsDAO extends JpaRepository<OrderDetailsEntity,String> {
}
