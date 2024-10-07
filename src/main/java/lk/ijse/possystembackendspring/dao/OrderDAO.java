package lk.ijse.possystembackendspring.dao;

import lk.ijse.possystembackendspring.entity.ItemEntity;
import lk.ijse.possystembackendspring.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<OrderEntity,String> {
}
