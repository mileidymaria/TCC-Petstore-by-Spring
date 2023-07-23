package org.csu.mypetstore.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.csu.mypetstore.domain.LineItem;
import java.util.List;

@Mapper
public interface LineItemMapper {
    List<LineItem> getLineItemsByOrderId(int orderId);
    void insertLineItem(LineItem lineItem);
}
