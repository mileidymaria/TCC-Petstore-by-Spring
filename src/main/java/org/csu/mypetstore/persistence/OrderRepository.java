package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Order;

import java.util.List;
import java.util.Map;

public interface OrderRepository {
    Map<String, String> create(Order order);

    Order getOrder(int orderId);

    List<Order> getOrdersByUsername(String username);

    int getNextId(String name);

    void confirmOrder(Order orderImpl);
}
