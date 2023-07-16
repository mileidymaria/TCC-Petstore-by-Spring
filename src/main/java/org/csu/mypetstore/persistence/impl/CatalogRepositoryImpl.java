package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.CatalogRepository;
import org.csu.mypetstore.persistence.mapper.CategoryMapper;
import org.csu.mypetstore.persistence.mapper.ItemMapper;
import org.csu.mypetstore.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CatalogRepositoryImpl implements CatalogRepository {
    @Autowired
    private final CategoryMapper categoryMapper;

    @Autowired
    private final ProductMapper productMapper;

    @Autowired
    private final ItemMapper itemMapper;

    public CatalogRepositoryImpl(CategoryMapper categoryMapper, ProductMapper productMapper, ItemMapper itemMapper) {
        this.categoryMapper = categoryMapper;
        this.productMapper = productMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public Category getCategory(String categoryId) {
        return categoryMapper.getCategory(categoryId);
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public Product getProduct(String productId) {
        return productMapper.getProduct(productId);
    }

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        return productMapper.getProductListByCategory(categoryId);
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
    public void updateInventoryQuantity(String itemId, int quantity){
        itemMapper.updateInventoryQuantity(itemId,quantity);
    }
}
