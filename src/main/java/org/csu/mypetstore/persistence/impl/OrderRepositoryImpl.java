package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.persistence.AccountRepository;
import org.csu.mypetstore.persistence.OrderRepository;
import org.csu.mypetstore.persistence.mapper.ItemMapper;
import org.csu.mypetstore.persistence.mapper.LineItemMapper;
import org.csu.mypetstore.persistence.mapper.OrderMapper;
import org.csu.mypetstore.persistence.mapper.SequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepositoryImpl implements OrderRepository {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private SequenceMapper sequenceMapper;
    @Autowired
    private LineItemMapper lineItemMapper;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Map<String, String> create(Order order){
        Map<String, String> result = new HashMap<>(2);
        if(accountRepository.get(order.getUsername()) != null){
            insertOrder(order);
            result.put("msg", "Thank you, your order has been submitted.");
            result.put("path", "order/ViewOrder");
            return result;
        }
        result.put("msg", "you may only view your own orders.");
        result.put("path", "common/error");
        return result;
    }

    private void insertOrder(Order orderImpl){
        orderImpl.setOrderId(getNextId("ordernum"));
        for(int i = 0; i < orderImpl.getLineItems().size(); i++){
            LineItem lineItem = (LineItem) orderImpl.getLineItems().get(i);
            String itemId = lineItem.getItemId();
            Integer increment = new Integer(lineItem.getQuantity());
            itemMapper.updateInventoryQuantity(itemId,increment);
        }
        orderMapper.insertOrder(orderImpl);
        orderMapper.insertOrderStatus(orderImpl);
        for(int i = 0; i< orderImpl.getLineItems().size(); i++){
            LineItem lineItem = (LineItem) orderImpl.getLineItems().get(i);
            lineItem.setOrderId(orderImpl.getOrderId());
            lineItemMapper.insertLineItem(lineItem);
        }
    }


    @Override
    public Order getOrder(int orderId){
        Order orderImpl = orderMapper.getOrder(orderId);
        orderImpl.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
        for(int i = 0; i < orderImpl.getLineItems().size(); i++){
            LineItem lineItem = (LineItem) orderImpl.getLineItems().get(i);
            Item item = itemMapper.getItem(lineItem.getItemId());
            item.setQuantity(itemMapper.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }
        return orderImpl;
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
    public void confirmOrder(Order orderImpl){
        orderMapper.insertOrder(orderImpl);
    }
}
