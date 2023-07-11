package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.common.Action;
import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.CatalogRepository;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public Category getCategory(String categoryId) {
        return catalogRepository.getCategory(categoryId);
    }

    @Override
    public Product getProduct(String productId) {
        return catalogRepository.getProduct(productId);
    }

    @Override
    public List<Product> getProductList(String categoryId) {
        return catalogRepository.getProductList(categoryId);
    }

    @Override
    public Map<String, Object> getProductListByCategory (String categoryId) {
        return catalogRepository.getProductListByCategory(categoryId);
    }

    @Override
    public List<Product> searchProductList(String keyword) {
        return catalogRepository.searchProductList("%" + keyword.toLowerCase() + "%");
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
    public void update(Map<String, String> argument, Action action) {
       catalogRepository.updateInventoryQuantity(argument);
    }
}
