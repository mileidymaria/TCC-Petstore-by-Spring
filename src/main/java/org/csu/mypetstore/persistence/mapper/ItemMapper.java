package org.csu.mypetstore.persistence.mapper;

import org.csu.mypetstore.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper {
    void updateInventoryQuantity(String itemId,int quantity);

    int getInventoryQuantity(String itemId);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);
}
