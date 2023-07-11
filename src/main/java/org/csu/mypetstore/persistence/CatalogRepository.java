package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;

import java.util.List;
import java.util.Map;

public interface CatalogRepository {
    Category getCategory(String categoryId);

    Product getProduct(String productId);

    List<Product> getProductList(String categoryId);

    Map<String, Object> getProductListByCategory(String categoryId);

    List<Product> searchProductList(String keyword);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);

    boolean isItemInStock(String itemId);

    void updateInventoryQuantity(Map<String, String> item);
}
