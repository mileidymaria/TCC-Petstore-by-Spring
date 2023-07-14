package org.csu.mypetstore.persistence.mapper;

import org.csu.mypetstore.domain.Order;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> getOrdersByUsername(String username);
    Order getOrder(int orderId);
    void insertOrder(Order orderImpl);
    void insertOrderStatus(Order orderImpl);
}
