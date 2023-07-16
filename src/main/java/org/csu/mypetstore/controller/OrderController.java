package org.csu.mypetstore.controller;

import org.csu.mypetstore.dto.AccountDTO;
import org.csu.mypetstore.dto.OrderDTO;
import org.csu.mypetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("order")
@SessionAttributes({"account","authenticated","myList","order"})
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("viewOrder")
    public  String viewOrder(OrderDTO order, Model model){
        return orderService.insertOrder(order, model);
    }
    @RequestMapping("confirm")
    public String newOrder(HttpServletRequest request, Model model){
        return orderService.newOrder(request, model);
    }

    @RequestMapping("newOrderForm")
    public String newOrderForm(AccountDTO account, @ModelAttribute("authenticated")boolean authenticated, Model model){
        return orderService.newOrderForm(account, authenticated, model);
    }
    @GetMapping("listOrders")
    public String listOrders(@ModelAttribute("account") AccountDTO account, Model model){
        model.addAttribute("orderList", orderService.getOrdersByUsername(account.getUsername()));
        return "order/ListOrders";
    }

}