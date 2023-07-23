package org.csu.mypetstore.repository;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CatalogRepository {
    Category getCategory(String categoryId);

    List<Category> getCategoryList();

    Product getProduct(String productId);

    List<Product> getProductListByCategory(String categoryId);

    List<Product> searchProductList(String keyword);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);

    boolean isItemInStock(String itemId);

    void updateInventoryQuantity(String itemId, int quantity);
}
