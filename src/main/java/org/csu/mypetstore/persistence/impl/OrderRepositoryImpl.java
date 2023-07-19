package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.persistence.AccountRepository;
import org.csu.mypetstore.persistence.OrderRepository;
import org.csu.mypetstore.persistence.mapper.ItemMapper;
import org.csu.mypetstore.persistence.mapper.LineItemMapper;
import org.csu.mypetstore.persistence.mapper.OrderMapper;
import org.csu.mypetstore.persistence.mapper.SequenceMapper;
import org.csu.mypetstore.utils.Action;
import org.csu.mypetstore.utils.Observable;
import org.csu.mypetstore.utils.Observer;
import org.csu.mypetstore.utils.Validator;
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
    
    private void create(Order order){
        if (Validator.getSoleInstance().isNull(accountRepository.get(order.getAccount().getUsername()))) {
            insertOrder(order);
            return;
        }
        throw new RuntimeException("Error! You may only view your own orders.");
    }

    private void insertOrder(Order order){
        order.setOrderId(getNextId("ordernum"));

        order.getLineItems().forEach(this::accept);

        orderMapper.insertOrder(order);
        orderMapper.insertOrderStatus(order);

        order.getLineItems().forEach(lineItem -> {
            lineItem.setOrderId(order.getOrderId());
            lineItemMapper.insertLineItem(lineItem);
        });

    }

    private Order getOrderWithLineItems(int orderId) {
        Order order = orderMapper.getOrder(orderId);
        order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
        return order;
    }

    private void populateLineItemWithItem(LineItem lineItem) {
        String itemId = lineItem.getItemId();
        Item item = itemMapper.getItem(itemId);
        int quantity = itemMapper.getInventoryQuantity(itemId);
        item.setQuantity(quantity);
        lineItem.setItem(item);
    }

    @Override
    public List<Order> getOrdersByUsername(String username){
        return orderMapper.getOrdersByUsername(username);
    }

    @Override
    public int getNextId(String name){
        Sequence sequence = sequenceMapper.getSequence(new Sequence(name, -1));
        if (Validator.getSoleInstance().isNull(sequence)) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }

        int nextId = sequence.getNextId() + 1;
        sequenceMapper.updateSequence(new Sequence(name, nextId));
        return nextId;
    }

    private void accept(LineItem lineItem) {
        itemMapper.updateInventoryQuantity(lineItem.getItemId(), lineItem.getQuantity());
    }


    @Override
    public void update(Order argument, Action action) {
        this.create(argument);
    }
}
