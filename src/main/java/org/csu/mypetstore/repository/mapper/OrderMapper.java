package org.csu.mypetstore.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.csu.mypetstore.domain.Order;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> getOrdersByUsername(String username);
    Order getOrder(int orderId);
    void insertOrder(Order orderImpl);
    void insertOrderStatus(Order orderImpl);
}
