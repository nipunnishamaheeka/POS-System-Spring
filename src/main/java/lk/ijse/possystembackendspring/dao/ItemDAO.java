package lk.ijse.possystembackendspring.dao;

import lk.ijse.possystembackendspring.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends JpaRepository<ItemEntity,String> {
}
