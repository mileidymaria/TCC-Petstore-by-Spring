package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.CatalogRepository;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public Category getCategory(String categoryId) {
        return catalogRepository.getCategory(categoryId);
    }

    @Override
    public List<Category> getCategoryList() {
        return catalogRepository.getCategoryList();
    }

    @Override
    public Product getProduct(String productId) {
        return catalogRepository.getProduct(productId);
    }

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        return catalogRepository.getProductListByCategory(categoryId);
    }

    @Override
    public List<Product> searchProductList(String keyword) {
        return catalogRepository.searchProductList(keyword);
    }

    @Override
    public List<Item> getItemListByProduct(String productId){
        return catalogRepository.getItemListByProduct(productId);
    }

    @Override
    public Item getItem(String itemId){
        return catalogRepository.getItem(itemId);
    }

    @Override
    public boolean isItemInStock(String itemId){
        return catalogRepository.isItemInStock(itemId);
    }

    @Override
    public void updateInventoryQuantity(String itemId, int quantity){
        catalogRepository.updateInventoryQuantity(itemId,quantity);
    }
}
