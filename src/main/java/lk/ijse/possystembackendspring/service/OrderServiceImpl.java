package lk.ijse.possystembackendspring.service;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendspring.dao.OrderDAO;
import lk.ijse.possystembackendspring.dto.OrderDto;
import lk.ijse.possystembackendspring.exception.DataPersistFailedException;
import lk.ijse.possystembackendspring.util.AppUtil;
import lk.ijse.possystembackendspring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderDAO orderDAO;
    @Autowired
    private final Mapping mapping;
    @Override
    public void saveOrders(OrderDto orderDto) {
        orderDto.setId(AppUtil.createOrderId());
        var orderEntity = mapping.convertToOrderEntity(orderDto);
        var savedOrder = orderDAO.save(orderEntity);
        if(savedOrder == null){
            throw new DataPersistFailedException("Cannot save");
        }
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return mapping.convertToOrderDTOList(orderDAO.findAll());
    }
}
