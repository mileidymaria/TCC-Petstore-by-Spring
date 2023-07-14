package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.persistence.OrderRepository;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Order order;

    @Autowired
    private Cart cart;

    @Autowired
    private AccountService accountService;

    private boolean confirmed;
    private boolean shippingAddressRequired;

    @Override
    public String insertOrder(Order order, Model model){
        Map<String, String> result = orderRepository.create(order);
        model.addAttribute("msg", result.get("msg"));
        return result.get("path");
    }

    @Override
    public void insertOrder(Order orderImpl) {
        orderRepository.create(order);
    }

    @Override
    public String newOrderForm(Account account, boolean authenticated, Model model){
        if(account == null || !authenticated){
            model.addAttribute("msg","You must sign on before attempting to check out.  Please sign on and try checking out again.");
            return "account/signon";
        }else if(cart !=null){
            order.initOrder(account, cart);
            return "order/NewOrderForm";
        }
        else{
            model.addAttribute("msg","An order could not be created because a cart could not be found.");
            return "common/error";
        }
    }

    @Override
    public String newOrder(HttpServletRequest request, Model model){
        if(request.getParameter("shippingAddressRequired") != null){
            shippingAddressRequired = false;
            model.addAttribute("order", order);
            return "order/ShippingForm";
        }else if(!confirmed){
            model.addAttribute("order", order);
            return "order/ConfirmOrder";
        }else if(order != null){
            insertOrder(order);
            model.addAttribute("msg","Thank you, your order has been submitted.");

            return "order/ViewOrder";
        }else{
            model.addAttribute("msg","An error occurred processing your order (order was null).");
            return "common/error";
        }
    }

    @Override
    public Order getOrder(int orderId){
        return orderRepository.getOrder(orderId);
    }

    @Override
    public List<Order> getOrdersByUsername(String username){
        return orderRepository.getOrdersByUsername(username);
    }

    @Override
    public int getNextId(String name){
        return orderRepository.getNextId(name);
    }

    @Override
    public void confirmOrder(Order order){
        orderRepository.create(order);
    }
}