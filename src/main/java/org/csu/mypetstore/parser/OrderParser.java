package org.csu.mypetstore.parser;

import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.dto.LineItemDTO;
import org.csu.mypetstore.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderParser {

    @Autowired
    private AccountParser accountMapper;

    @Autowired
    private ProductParser productMapper;

    public List<OrderDTO> toOrderDTOList (List<Order> orderList){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order orderToParse : orderList){
            orderDTOList.add(toOrderDTO(orderToParse));
        }
        return orderDTOList;
    }

    public Order toOrder (OrderDTO order) {
        return new Order(
                order.getOrderId(),
                order.getOrderDate(),
                accountMapper.toAccount(order.getAccountDTO()),
                order.getCreditCard(),
                order.getExpiryDate(),
                order.getCardType(),
                order.getLocale(),
                order.getStatus(),
                order.getCourier(),
                order.getTotalPrice(),
                toLineItemList(order.getLineItems())
        );
    }

    public List<LineItem> toLineItemList (List<LineItemDTO> lineItemDTOList){
        List<LineItem> lineItemList = new ArrayList<>();
        for (LineItemDTO lineItem : lineItemDTOList){
            lineItemList.add(toLineItemDTO(lineItem));
        }
        return lineItemList;
    }

    public LineItem toLineItemDTO (LineItemDTO lineItem){
        return new LineItem(
                lineItem.getOrderId(),
                lineItem.getLineNumber(),
                lineItem.getQuantity(),
                lineItem.getItemId(),
                lineItem.getUnitPrice(),
                productMapper.toItem(lineItem.getItem()),
                lineItem.getTotal()
        );
    }

    public OrderDTO toOrderDTO (Order order) {
        return new OrderDTO(
                order.getOrderId(),
                order.getOrderDate(),
                accountMapper.toAccountDTO(order.getAccount()),
                order.getCreditCard(),
                order.getExpiryDate(),
                order.getCardType(),
                order.getLocale(),
                order.getStatus(),
                order.getCourier(),
                order.getTotalPrice(),
                toLineItemDTOList(order.getLineItems())
        );
    }

    public List<LineItemDTO> toLineItemDTOList (List<LineItem> lineItemList){
        List<LineItemDTO> lineItemDTOList = new ArrayList<>();
        for (LineItem lineItem : lineItemList){
            lineItemDTOList.add(toLineItemDTO(lineItem));
        }
        return lineItemDTOList;
    }

    public LineItemDTO toLineItemDTO (LineItem lineItem){
        return new LineItemDTO(
                lineItem.getOrderId(),
                lineItem.getLineNumber(),
                lineItem.getQuantity(),
                lineItem.getItemId(),
                lineItem.getUnitPrice(),
                productMapper.toItemDTO(lineItem.getItem()),
                lineItem.getTotal()
        );
    }
}

