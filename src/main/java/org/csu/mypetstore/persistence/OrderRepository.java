package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Order;

import java.util.List;
import java.util.Map;

public interface OrderRepository {
    Map<String, String> create(Order order);

    List<Order> getOrdersByUsername(String username);

    int getNextId(String name);

}
