package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.dto.AccountDTO;
import org.csu.mypetstore.dto.OrderDTO;
import org.csu.mypetstore.parser.AccountParser;
import org.csu.mypetstore.parser.OrderParser;
import org.csu.mypetstore.repository.OrderRepository;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.OrderService;
import org.csu.mypetstore.utils.Action;
import org.csu.mypetstore.utils.Observable;
import org.csu.mypetstore.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OrderServiceImpl extends Observable implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private Order order;

    @Autowired
    private Cart cart;

    @Autowired
    private OrderParser orderMapper;

    @Autowired
    private AccountParser accountMapper;

    @Autowired
    private AccountService accountService;

    private boolean confirmed;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
        this.observers.add(this.orderRepository);
    }

    @Override
    public  String viewOrder(OrderDTO orderDTO, Model model){
        String path = "";
        if(accountService.getAccount(orderDTO.getUsername()).getUsername().equals(orderDTO.getUsername())){
            orderDTO.setOrderId(orderRepository.getNextId("ordernum"));
            this.notifyObservers(orderMapper.toOrder(orderDTO), Action.CREATE);
            model.addAttribute("msg","Thank you, your order has been submitted.");

            path = "order/ViewOrder";
        }else{
            orderDTO = null;
            model.addAttribute("msg","you may only view your own orders.");
            path = "common/error";
        }
        return path;
    }

    @Override
    public String insertOrder(OrderDTO order, Model model) {
        String path = "";
        try {
            this.notifyObservers(orderMapper.toOrder(order), Action.CREATE);
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
        Account account = (Account) model.getAttribute("account");
        if (!Validator.getSoleInstance().isNull(request.getParameter("shippingAddressRequired"))) {
            model.addAttribute("order", orderMapper.toOrderDTO(order));
            path = "order/ShippingForm";
        } else if (!confirmed) {
            order.initOrder(account, cart);
            model.addAttribute("order", orderMapper.toOrderDTO(order));
            path = "order/ConfirmOrder";
        } else if (!Validator.getSoleInstance().isNull(order)) {
            order.setOrderId(orderRepository.getNextId("ordernum"));
            this.notifyObservers(orderMapper.toOrderDTO(order), Action.CREATE);
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