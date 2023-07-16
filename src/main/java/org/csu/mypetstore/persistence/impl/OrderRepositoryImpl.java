package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.persistence.AccountRepository;
import org.csu.mypetstore.persistence.OrderRepository;
import org.csu.mypetstore.persistence.mapper.ItemMapper;
import org.csu.mypetstore.persistence.mapper.LineItemMapper;
import org.csu.mypetstore.persistence.mapper.OrderMapper;
import org.csu.mypetstore.persistence.mapper.SequenceMapper;
import org.csu.mypetstore.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepositoryImpl implements OrderRepository {
    @Autowired
    private final ItemMapper itemMapper;
    @Autowired
    private final OrderMapper orderMapper;
    @Autowired
    private final SequenceMapper sequenceMapper;
    @Autowired
    private final LineItemMapper lineItemMapper;
    @Autowired
    private final AccountRepository accountRepository;

    public OrderRepositoryImpl(ItemMapper itemMapper, OrderMapper orderMapper, SequenceMapper sequenceMapper, LineItemMapper lineItemMapper, AccountRepository accountRepository) {
        this.itemMapper = itemMapper;
        this.orderMapper = orderMapper;
        this.sequenceMapper = sequenceMapper;
        this.lineItemMapper = lineItemMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public Map<String, String> create(Order order){
        Map<String, String> result = new HashMap<>(2);
        if (Validator.getSoleInstance().isNull(accountRepository.get(order.getUsername()))) {
            insertOrder(order);
            result.put("msg", "Thank you, your order has been submitted.");
            result.put("path", "order/ViewOrder");
        } else {
            result.put("msg", "you may only view your own orders.");
            result.put("path", "common/error");
        }
        return result;
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


    @Override
    public Order getOrder(int orderId){
        Order order = getOrderWithLineItems(orderId);

        for (LineItem lineItem : order.getLineItems()) {
            populateLineItemWithItem(lineItem);
        }

        return order;
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

    @Override
    public void confirmOrder(Order orderImpl){
        orderMapper.insertOrder(orderImpl);
    }

    private void accept(LineItem lineItem) {
        itemMapper.updateInventoryQuantity(lineItem.getItemId(), lineItem.getQuantity());
    }
}
