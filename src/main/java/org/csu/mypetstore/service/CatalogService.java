package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.dto.CategoryDTO;
import org.csu.mypetstore.dto.ItemDTO;
import org.csu.mypetstore.dto.ProductDTO;

import java.util.List;

public interface CatalogService {
    CategoryDTO getCategory(String categoryId);

    ProductDTO getProduct(String productId);

    List<ProductDTO> getProductListByCategory(String categoryId);

    List<ProductDTO> searchProductList(String keyword);

    List<ItemDTO> getItemListByProduct(String productId);

    ItemDTO getItem(String itemId);

    boolean isItemInStock(String itemId);

    void updateInventoryQuantity(String itemId, int quantity);
}
