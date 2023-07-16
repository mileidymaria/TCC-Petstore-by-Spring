package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.dto.CategoryDTO;
import org.csu.mypetstore.dto.ItemDTO;
import org.csu.mypetstore.dto.ProductDTO;
import org.csu.mypetstore.persistence.CatalogRepository;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public CategoryDTO getCategory(String categoryId) {
        return toCategoryDTO(catalogRepository.getCategory(categoryId));
    }

    @Override
    public List<CategoryDTO> getCategoryList() {
        return toCategoryDTOList(catalogRepository.getCategoryList());
    }

    @Override
    public ProductDTO getProduct(String productId) {
        return toProductDTO(catalogRepository.getProduct(productId));
    }

    @Override
    public List<ProductDTO> getProductListByCategory(String categoryId) {
        return toProductDTOList(catalogRepository.getProductListByCategory(categoryId));
    }

    @Override
    public List<ProductDTO> searchProductList(String keyword) {
        return toProductDTOList(catalogRepository.searchProductList(keyword));
    }

    @Override
    public List<ItemDTO> getItemListByProduct(String productId){
        return toItemDTOList(catalogRepository.getItemListByProduct(productId));
    }

    @Override
    public ItemDTO getItem(String itemId){
        return toItemDTO(catalogRepository.getItem(itemId));
    }

    @Override
    public boolean isItemInStock(String itemId){
        return catalogRepository.isItemInStock(itemId);
    }

    @Override
    public void updateInventoryQuantity(String itemId, int quantity){
        catalogRepository.updateInventoryQuantity(itemId,quantity);
    }

    private List<ProductDTO> toProductDTOList(List<Product> productList){
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product product : productList){
            productDTOList.add(toProductDTO(product));
        }
        return productDTOList;
    }

    private ProductDTO toProductDTO(Product product){
        return new ProductDTO(
                product.getProductId(),
                product.getCategoryId(),
                product.getName(),
                product.getDescription(),
                product.getDescriptionImage(),
                product.getDescriptionText()
        );
    }

    private Product toProduct(ProductDTO product){
        return new Product(
                product.getProductId(),
                product.getCategoryId(),
                product.getName(),
                product.getDescription(),
                product.getDescriptionImage(),
                product.getDescriptionText()
        );
    }

    private List<ItemDTO> toItemDTOList (List<Item> itemList){
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : itemList){
            itemDTOList.add(toItemDTO(item));
        }
        return itemDTOList;
    }

    @Override
    public ItemDTO toItemDTO(Item item){
        return new ItemDTO(
                item.getItemId(),
                item.getProductId(),
                item.getListPrice(),
                item.getUnitCost(),
                item.getSupplierId(),
                item.getStatus(),
                item.getAttribute1(),
                item.getAttribute2(),
                item.getAttribute3(),
                item.getAttribute4(),
                item.getAttribute5(),
                toProductDTO(item.getProduct()),
                item.getQuantity()
        );
    }

    @Override
    public Item toItem(ItemDTO item){
        return new Item (
                item.getItemId(),
                item.getProductId(),
                item.getListPrice(),
                item.getUnitCost(),
                item.getSupplierId(),
                item.getStatus(),
                item.getAttribute1(),
                item.getAttribute2(),
                item.getAttribute3(),
                item.getAttribute4(),
                item.getAttribute5(),
                toProduct(item.getProduct()),
                item.getQuantity()
        );
    }

    private List<CategoryDTO> toCategoryDTOList (List<Category> categoryList){
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryList){
            categoryDTOList.add(toCategoryDTO(category));
        }
        return categoryDTOList;
    }

    private CategoryDTO toCategoryDTO(Category category) {
        return new CategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription()
        );
    }
}
