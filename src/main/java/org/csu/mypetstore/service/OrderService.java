package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {
    String insertOrder(Order orderImpl, Model model);

    void insertOrder(Order orderImpl);

    String newOrderForm(Account account, boolean authenticated, Model model);

    String newOrder(HttpServletRequest request, Model model);

    Order getOrder(int orderId);

    List<Order> getOrdersByUsername(String username);

    int getNextId(String name);

    void confirmOrder(Order orderImpl);
}
