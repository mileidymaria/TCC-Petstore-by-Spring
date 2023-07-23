package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.dto.CategoryDTO;
import org.csu.mypetstore.dto.ItemDTO;
import org.csu.mypetstore.dto.ProductDTO;
import org.csu.mypetstore.parser.ProductParser;
import org.csu.mypetstore.repository.CatalogRepository;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ProductParser productMapper;

    @Override
    public CategoryDTO getCategory(String categoryId) {
        return productMapper.toCategoryDTO(catalogRepository.getCategory(categoryId));
    }

    @Override
    public ProductDTO getProduct(String productId) {
        return productMapper.toProductDTO(catalogRepository.getProduct(productId));
    }

    @Override
    public List<ProductDTO> getProductListByCategory(String categoryId) {
        return productMapper.toProductDTOList(catalogRepository.getProductListByCategory(categoryId));
    }

    @Override
    public List<ProductDTO> searchProductList(String keyword) {
        return productMapper.toProductDTOList(catalogRepository.searchProductList(keyword));
    }

    @Override
    public List<ItemDTO> getItemListByProduct(String productId){
        return productMapper.toItemDTOList(catalogRepository.getItemListByProduct(productId));
    }

    @Override
    public ItemDTO getItem(String itemId){
        return productMapper.toItemDTO(catalogRepository.getItem(itemId));
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
