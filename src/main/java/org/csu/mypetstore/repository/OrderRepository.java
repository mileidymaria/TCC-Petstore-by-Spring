package org.csu.mypetstore.repository;

import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.utils.Observer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderRepository extends Observer<Order>{

    List<Order> getOrdersByUsername(String username);

    int getNextId(String name);

}
