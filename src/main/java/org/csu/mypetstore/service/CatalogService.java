package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.dto.CategoryDTO;
import org.csu.mypetstore.dto.ItemDTO;
import org.csu.mypetstore.dto.ProductDTO;
import org.springframework.ui.Model;

import java.util.List;

public interface CatalogService {
    CategoryDTO getCategory(String categoryId);

    String viewCategory(String categoryId, Model model);

    ItemDTO getItem(String itemId);

    String viewProduct(String productId, Model model);

    ProductDTO getProduct(String productId);

    List<ProductDTO> getProductListByCategory(String categoryId);

    List<ItemDTO> getItemListByProduct(String productId);

    String getItem(String itemId, Model model);

    boolean isItemInStock(String itemId);

    void updateInventoryQuantity(String itemId, int quantity);

    String searchProducts(String keyword, Model model);
}
