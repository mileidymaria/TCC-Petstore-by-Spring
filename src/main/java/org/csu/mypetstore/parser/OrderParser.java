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

    public Order toOrder (OrderDTO orderDTO) {
        List<LineItem> lineItems = toLineItemList(orderDTO.getLineItems());

        return new Order(
                orderDTO.getOrderId(),
                orderDTO.getUsername(),
                orderDTO.getOrderDate(),
                orderDTO.getShipAddress1(),
                orderDTO.getShipAddress2(),
                orderDTO.getShipCity(),
                orderDTO.getShipState(),
                orderDTO.getShipZip(),
                orderDTO.getShipCountry(),
                orderDTO.getBillAddress1(),
                orderDTO.getBillAddress2(),
                orderDTO.getBillCity(),
                orderDTO.getBillState(),
                orderDTO.getBillZip(),
                orderDTO.getBillCountry(),
                orderDTO.getCourier(),
                orderDTO.getTotalPrice(),
                orderDTO.getBillToFirstName(),
                orderDTO.getBillToLastName(),
                orderDTO.getShipToFirstName(),
                orderDTO.getShipToLastName(),
                orderDTO.getCreditCard(),
                orderDTO.getExpiryDate(),
                orderDTO.getCardType(),
                orderDTO.getLocale(),
                orderDTO.getStatus(),
                lineItems
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

