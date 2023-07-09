package org.csu.mypetstore.service;

import org.csu.mypetstore.common.Action;
import org.csu.mypetstore.common.Observer;
import org.csu.mypetstore.controller.OrderController;
import org.csu.mypetstore.domain.Order;

import java.util.List;

public interface OrderService extends Observer<Order, Action>{

    Order getOrder(int orderId);

    List<Order> getOrdersByUsername(String username);

    int getNextId(String name);
}
