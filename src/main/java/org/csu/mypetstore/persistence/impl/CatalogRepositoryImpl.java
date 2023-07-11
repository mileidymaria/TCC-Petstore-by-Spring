package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.CatalogRepository;
import org.csu.mypetstore.persistence.mapper.CategoryMapper;
import org.csu.mypetstore.persistence.mapper.ItemMapper;
import org.csu.mypetstore.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogRepositoryImpl implements CatalogRepository {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Category getCategory(String categoryId) {
        return categoryMapper.getCategory(categoryId);
    }

    @Override
    public Product getProduct(String productId) {
        return productMapper.getProduct(productId);
    }

    @Override
    public List<Product> getProductList(String categoryId) {
        return productMapper.getProductListByCategory(categoryId);
    }

    @Override
    public Map<String, Object> getProductListByCategory (String categoryId) {
        Map<String, Object> productListByCategory = new HashMap<>(2);
        productListByCategory.put("category", categoryMapper.getCategory(categoryId));
        productListByCategory.put("productList", productMapper.getProductListByCategory(categoryId));
        return productListByCategory;
    }

    @Override
    public List<Product> searchProductList(String keyword) {
        return productMapper.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    @Override
    public List<Item> getItemListByProduct(String productId){
        return itemMapper.getItemListByProduct(productId);
    }

    @Override
    public Item getItem(String itemId){
        return itemMapper.getItem(itemId);
    }

    @Override
    public boolean isItemInStock(String itemId){
        return itemMapper.getInventoryQuantity(itemId) > 0;
    }

    @Override
    public void updateInventoryQuantity(Map<String, String> item){
        itemMapper.updateInventoryQuantity(item.get("itemId").toString(), Integer.parseInt(item.get("quantity")));
    }

}
