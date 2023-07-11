package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.OrderRepository;
import org.csu.mypetstore.persistence.mapper.ItemMapper;
import org.csu.mypetstore.persistence.mapper.LineItemMapper;
import org.csu.mypetstore.persistence.mapper.OrderMapper;
import org.csu.mypetstore.persistence.mapper.SequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private SequenceMapper sequenceMapper;
    @Autowired
    private LineItemMapper lineItemMapper;

    @Override
    public void insertOrder(Order order) {
        order.setOrderId(getNextId("ordernum"));
        for(LineItem lineItem : order.getLineItems()){
            itemMapper.updateInventoryQuantity(lineItem.getItemId(),lineItem.getQuantity());
        }
        orderMapper.insertOrder(order);
        orderMapper.insertOrderStatus(order);
        for(LineItem lineItem : order.getLineItems()){
            lineItem.setOrderId(order.getOrderId());
            lineItemMapper.insertLineItem(lineItem);
        }
    }

    @Override
    public Order getOrder(int orderId) {
        return null;
    }

    @Override
    public int getNextId(String name) {
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
    public List<Order> getOrdersByUsername(String username) {
        return orderMapper.getOrdersByUsername(username);
    }
}
