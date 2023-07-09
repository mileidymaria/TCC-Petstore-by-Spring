package org.csu.mypetstore.service;

import org.csu.mypetstore.common.Action;
import org.csu.mypetstore.common.Observer;
import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;

import java.util.List;
import java.util.Map;

public interface CatalogService extends Observer<Map<String, String>, Action> {
    Category getCategory(String categoryId);

    List<Category> getCategoryList();

    Product getProduct(String productId);

    List<Product> getProductListByCategory(String categoryId);

    List<Product> searchProductList(String keyword);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);

    boolean isItemInStock(String itemId);
}
