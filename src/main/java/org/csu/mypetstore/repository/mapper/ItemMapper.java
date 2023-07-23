package org.csu.mypetstore.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.csu.mypetstore.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ItemMapper {
    void updateInventoryQuantity(String itemId,int quantity);

    int getInventoryQuantity(String itemId);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);
}
