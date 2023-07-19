package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.utils.Observer;

import java.util.List;
import java.util.Map;

public interface OrderRepository extends Observer<Order>{

    List<Order> getOrdersByUsername(String username);

    int getNextId(String name);

}
