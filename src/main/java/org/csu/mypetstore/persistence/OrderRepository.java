package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderRepository {
    void insertOrder(Order order);

    Order getOrder(int orderId);

    int getNextId(String name);

    List<Order> getOrdersByUsername(String username);
}
