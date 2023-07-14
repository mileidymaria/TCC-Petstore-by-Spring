package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("order")
@SessionAttributes({"account","authenticated","myList","order"})
public class OrderController {
    @Autowired
    private OrderService orderService;

    private static final List<String>CARD_TYPE_LIST;
    static {
        List<String>cardList = new ArrayList<String>();
        cardList.add("Visa");
        cardList.add("MasterCard");
        cardList.add("American Express");
        CARD_TYPE_LIST = Collections.unmodifiableList(cardList);
    }
    @GetMapping("viewOrder")
    public  String viewOrder(Order orderImpl, Model model){
        return orderService.insertOrder(orderImpl, model);
    }
    @RequestMapping("confirm")
    public String newOrder(HttpServletRequest request, Model model){
        return orderService.newOrder(request, model);
    }

    @RequestMapping("newOrderForm")
    public String newOrderForm(Account account, @ModelAttribute("authenticated")boolean authenticated, Model model){
        return orderService.newOrderForm(account, authenticated, model);
    }
    @GetMapping("listOrders")
    public String listOrders(@ModelAttribute("account") Account account, Model model){
        model.addAttribute("orderList", orderService.getOrdersByUsername(account.getUsername()));
        return "order/ListOrders";
    }

}