package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.common.Action;
import org.csu.mypetstore.common.Observable;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.OrderRepository;
import org.csu.mypetstore.persistence.mapper.ItemMapper;
import org.csu.mypetstore.persistence.mapper.LineItemMapper;
import org.csu.mypetstore.persistence.mapper.OrderMapper;
import org.csu.mypetstore.persistence.mapper.SequenceMapper;
import org.csu.mypetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends Observable implements OrderService {

    private OrderRepository orderRepository;

    private void insertOrder(Order order){
        orderRepository.insertOrder(order);
    }

    @Override
    public Order getOrder(int orderId){
        return orderRepository.getOrder(orderId);
    }

    @Override
    public List<Order> getOrdersByUsername(String username){
        return orderRepository.getOrdersByUsername(username);
    }

    @Override
    public int getNextId(String name){
        return orderRepository.getNextId(name);
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