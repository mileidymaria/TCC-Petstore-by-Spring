package org.csu.mypetstore.service;

import org.csu.mypetstore.dto.AccountDTO;
import org.csu.mypetstore.dto.OrderDTO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {
    String viewOrder(OrderDTO orderDTO, Model model);

    String newOrderForm(AccountDTO account, boolean authenticated, Model model);

    String newOrder(HttpServletRequest request, Model model);

    List<OrderDTO> getOrdersByUsername(String username);
}
