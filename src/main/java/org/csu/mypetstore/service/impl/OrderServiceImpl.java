package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.common.Action;
import org.csu.mypetstore.common.Observable;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.ItemMapper;
import org.csu.mypetstore.persistence.LineItemMapper;
import org.csu.mypetstore.persistence.OrderMapper;
import org.csu.mypetstore.persistence.SequenceMapper;
import org.csu.mypetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends Observable implements OrderService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private SequenceMapper sequenceMapper;
    @Autowired
    private LineItemMapper lineItemMapper;

    private void insertOrder(Order order){
        order.setOrderId(getNextId("ordernum"));
        for(int i = 0; i < order.getLineItems().size(); i++){
            LineItem lineItem = (LineItem)order.getLineItems().get(i);
            String itemId = lineItem.getItemId();
            Integer increment = new Integer(lineItem.getQuantity());
            itemMapper.updateInventoryQuantity(itemId,increment);
        }
        orderMapper.insertOrder(order);
        orderMapper.insertOrderStatus(order);
        for(int i = 0; i< order.getLineItems().size(); i++){
            LineItem lineItem = (LineItem)order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            lineItemMapper.insertLineItem(lineItem);
        }
    }

    @Override
    public Order getOrder(int orderId){
        Order order = orderMapper.getOrder(orderId);
        order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
        for(int i = 0 ; i < order.getLineItems().size();i++){
            LineItem lineItem = (LineItem)order.getLineItems().get(i);
            Item item = itemMapper.getItem(lineItem.getItemId());
            item.setQuantity(itemMapper.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }
        return order;
    }

    @Override
    public List<Order> getOrdersByUsername(String username){
        return orderMapper.getOrdersByUsername(username);
    }

    @Override
    public int getNextId(String name){
        Sequence sequence = new Sequence(name,-1);
        sequence = (Sequence)sequenceMapper.getSequence(sequence);
        if(sequence == null){
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name,sequence.getNextId() + 1);
        sequenceMapper.updateSequence((parameterObject));
        return sequence.getNextId();
    }

    @Override
    public void update(Order order, Action action) {
        if (action.equals(Action.CREATE)){
            insertOrder(order);
            return;
        }
        if (action.equals(Action.UPDATE)){
            insertOrder(order);
        }
    }
}