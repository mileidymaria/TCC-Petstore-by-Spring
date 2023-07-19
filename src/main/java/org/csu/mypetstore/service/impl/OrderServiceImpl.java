package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.dto.AccountDTO;
import org.csu.mypetstore.dto.LineItemDTO;
import org.csu.mypetstore.dto.OrderDTO;
import org.csu.mypetstore.mapper.AccountMapper;
import org.csu.mypetstore.mapper.OrderMapper;
import org.csu.mypetstore.persistence.OrderRepository;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.OrderService;
import org.csu.mypetstore.utils.Observable;
import org.csu.mypetstore.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends Observable implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Order order;

    @Autowired
    private Cart cart;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AccountMapper accountMapper;

    private boolean confirmed;

    public OrderServiceImpl(){
        this.observers.add(orderRepository);
    }

    @Override
    public String insertOrder(OrderDTO order, Model model) {
        String path = "";
        try {
            this.notifyObservers(orderMapper.toOrder(order));
            model.addAttribute("msg", "Thank you, your order has been submitted.");
            path = "order/ViewOrder";
        } catch (RuntimeException exception) {
            model.addAttribute("msg", exception.getMessage());
            path = "common/error";
        }
        return path;
    }

    @Override
    public String newOrderForm(AccountDTO account, boolean authenticated, Model model) {
        String path = "";
        if (Validator.getSoleInstance().isNull(account) || !authenticated) {
            model.addAttribute("msg", "You must sign on before attempting to check out.  Please sign on and try checking out again.");
            path = "account/signon";
        } else if (!Validator.getSoleInstance().isNull(cart)) {
            order.initOrder(accountMapper.toAccount(account), cart);
            path = "order/NewOrderForm";
        } else {
            model.addAttribute("msg", "An order could not be created because a cart could not be found.");
            path = "common/error";
        }
        return path;
    }

    @Override
    public String newOrder(HttpServletRequest request, Model model) {
        String path = "";
        if (!Validator.getSoleInstance().isNull(request.getParameter("shippingAddressRequired"))) {
            model.addAttribute("order", order);
            path = "order/ShippingForm";
        } else if (!confirmed) {
            model.addAttribute("order", order);
            path = "order/ConfirmOrder";
        } else if (!Validator.getSoleInstance().isNull(order)) {
            this.notifyObservers(orderMapper.toOrderDTO(order));
            model.addAttribute("msg", "Thank you, your order has been submitted.");
            path = "order/ViewOrder";
        } else {
            model.addAttribute("msg", "An error occurred processing your order (order was null).");
            path = "common/error";
        }
        return path;
    }

    @Override
    public List<OrderDTO> getOrdersByUsername(String username) {
        return orderMapper.toOrderDTOList(orderRepository.getOrdersByUsername(username));
    }

}