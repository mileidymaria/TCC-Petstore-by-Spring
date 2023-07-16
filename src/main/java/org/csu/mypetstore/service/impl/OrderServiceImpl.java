package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.dto.AccountDTO;
import org.csu.mypetstore.dto.LineItemDTO;
import org.csu.mypetstore.dto.OrderDTO;
import org.csu.mypetstore.persistence.OrderRepository;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.OrderService;
import org.csu.mypetstore.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final Order order;

    @Autowired
    private final Cart cart;

    @Autowired
    private final AccountService accountService;

    @Autowired
    private final CatalogService catalogService;

    private boolean confirmed;

    public OrderServiceImpl(OrderRepository orderRepository, Order order, Cart cart, AccountService accountService, CatalogService catalogService) {
        this.orderRepository = orderRepository;
        this.order = order;
        this.cart = cart;
        this.accountService = accountService;
        this.catalogService = catalogService;
    }

    @Override
    public String insertOrder(OrderDTO order, Model model){
        Map<String, String> result = orderRepository.create(toOrder(order));
        model.addAttribute("msg", result.get("msg"));
        return result.get("path");
    }

    @Override
    public void insertOrder(OrderDTO order) {
        orderRepository.create(toOrder(order));
    }

    @Override
    public String newOrderForm(AccountDTO account, boolean authenticated, Model model){
        String path = "";
        if(Validator.getSoleInstance().isNull(account) || !authenticated){
            model.addAttribute("msg","You must sign on before attempting to check out.  Please sign on and try checking out again.");
            path = "account/signon";
        } else if(!Validator.getSoleInstance().isNull(cart)){
            order.initOrder(accountService.toAccount(account), cart);
            path = "order/NewOrderForm";
        }
        else{
            model.addAttribute("msg","An order could not be created because a cart could not be found.");
            path = "common/error";
        }
        return path;
    }

    @Override
    public String newOrder(HttpServletRequest request, Model model){
        String path = "";
        if(!Validator.getSoleInstance().isNull(request.getParameter("shippingAddressRequired"))){
            model.addAttribute("order", order);
            path = "order/ShippingForm";
        } else if(!confirmed){
            model.addAttribute("order", order);
            path = "order/ConfirmOrder";
        } else if(!Validator.getSoleInstance().isNull(order)){
            insertOrder(toOrderDTO(order));
            model.addAttribute("msg","Thank you, your order has been submitted.");
            path = "order/ViewOrder";
        } else{
            model.addAttribute("msg","An error occurred processing your order (order was null).");
            path = "common/error";
        }
        return path;
    }

    @Override
    public OrderDTO getOrder(int orderId){
        return toOrderDTO(orderRepository.getOrder(orderId));
    }

    @Override
    public List<OrderDTO> getOrdersByUsername(String username){
        return toOrderDTOList(orderRepository.getOrdersByUsername(username));
    }

    @Override
    public int getNextId(String name){
        return orderRepository.getNextId(name);
    }

    @Override
    public void confirmOrder(OrderDTO order){
        orderRepository.create(toOrder(order));
    }

    private List<OrderDTO> toOrderDTOList (List<Order> orderList){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order orderToParse : orderList){
            orderDTOList.add(toOrderDTO(orderToParse));
        }
        return orderDTOList;
    }

    private Order toOrder (OrderDTO order) {
        return new Order(
                order.getOrderId(),
                order.getUsername(),
                order.getOrderDate(),
                order.getShipAddress1(),
                order.getShipAddress2(),
                order.getShipCity(),
                order.getShipState(),
                order.getShipZip(),
                order.getShipCountry(),
                order.getBillAddress1(),
                order.getBillAddress2(),
                order.getBillCity(),
                order.getBillState(),
                order.getBillZip(),
                order.getBillCountry(),
                order.getCourier(),
                order.getTotalPrice(),
                order.getBillToFirstName(),
                order.getBillToLastName(),
                order.getShipToFirstName(),
                order.getShipToLastName(),
                order.getCreditCard(),
                order.getExpiryDate(),
                order.getCardType(),
                order.getLocale(),
                order.getStatus(),
                toLineItemList(order.getLineItems())
        );
    }

    private List<LineItem> toLineItemList (List<LineItemDTO> lineItemDTOList){
        List<LineItem> lineItemList = new ArrayList<>();
        for (LineItemDTO lineItem : lineItemDTOList){
            lineItemList.add(toLineItemDTO(lineItem));
        }
        return lineItemList;
    }

    private LineItem toLineItemDTO (LineItemDTO lineItem){
        return new LineItem(
                lineItem.getOrderId(),
                lineItem.getLineNumber(),
                lineItem.getQuantity(),
                lineItem.getItemId(),
                lineItem.getUnitPrice(),
                catalogService.toItem(lineItem.getItem()),
                lineItem.getTotal()
        );
    }

    private OrderDTO toOrderDTO (Order order) {
        return new OrderDTO(
                order.getOrderId(),
                order.getUsername(),
                order.getOrderDate(),
                order.getShipAddress1(),
                order.getShipAddress2(),
                order.getShipCity(),
                order.getShipState(),
                order.getShipZip(),
                order.getShipCountry(),
                order.getBillAddress1(),
                order.getBillAddress2(),
                order.getBillCity(),
                order.getBillState(),
                order.getBillZip(),
                order.getBillCountry(),
                order.getCourier(),
                order.getTotalPrice(),
                order.getBillToFirstName(),
                order.getBillToLastName(),
                order.getShipToFirstName(),
                order.getShipToLastName(),
                order.getCreditCard(),
                order.getExpiryDate(),
                order.getCardType(),
                order.getLocale(),
                order.getStatus(),
                toLineItemDTOList(order.getLineItems())
        );
    }

    private List<LineItemDTO> toLineItemDTOList (List<LineItem> lineItemList){
        List<LineItemDTO> lineItemDTOList = new ArrayList<>();
        for (LineItem lineItem : lineItemList){
            lineItemDTOList.add(toLineItemDTO(lineItem));
        }
        return lineItemDTOList;
    }

    private LineItemDTO toLineItemDTO (LineItem lineItem){
        return new LineItemDTO(
                lineItem.getOrderId(),
                lineItem.getLineNumber(),
                lineItem.getQuantity(),
                lineItem.getItemId(),
                lineItem.getUnitPrice(),
                catalogService.toItemDTO(lineItem.getItem()),
                lineItem.getTotal()
        );
    }
}