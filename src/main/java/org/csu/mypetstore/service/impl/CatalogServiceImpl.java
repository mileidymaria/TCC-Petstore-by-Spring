package org.csu.mypetstore.service.impl;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.dto.CategoryDTO;
import org.csu.mypetstore.dto.ItemDTO;
import org.csu.mypetstore.dto.ProductDTO;
import org.csu.mypetstore.parser.ProductParser;
import org.csu.mypetstore.repository.CatalogRepository;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    public String viewCategory(String categoryId, Model model) {
        if (!Validator.getSoleInstance().isNull(categoryId)) {
            List<ProductDTO> productList = getProductListByCategory(categoryId);
            CategoryDTO category = getCategory(categoryId);
            model.addAttribute("productList", productList);
            model.addAttribute("category", category);
        }
        return "catalog/category";
    }

    @Override
    public ItemDTO getItem(String itemId){
        return productMapper.toItemDTO(catalogRepository.getItem(itemId));
    }

    @Override
    public String viewProduct(String productId, Model model) {
        if (!Validator.getSoleInstance().isNull(productId)) {
            List<ItemDTO> itemList = getItemListByProduct(productId);
            ProductDTO product = getProduct(productId);
            model.addAttribute("product", product);
            model.addAttribute("itemList", itemList);
        }
        return "catalog/product";
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
    public List<ItemDTO> getItemListByProduct(String productId){
        return productMapper.toItemDTOList(catalogRepository.getItemListByProduct(productId));
    }

    @Override
    public String getItem(String itemId, Model model){
        if(!Validator.getSoleInstance().isNull(itemId)) {
            ItemDTO item = productMapper.toItemDTO(catalogRepository.getItem(itemId));
            ProductDTO product = productMapper.toProductDTO(Product.processProductDescription(productMapper.toProduct(item.getProduct())));
            model.addAttribute("item",item);
            model.addAttribute("product",product);
        }
        return "catalog/item";
    }

    @Override
    public boolean isItemInStock(String itemId){
        return catalogRepository.isItemInStock(itemId);
    }

    @Override
    public void updateInventoryQuantity(String itemId, int quantity) {
        catalogRepository.updateInventoryQuantity(itemId,quantity);
    }

    @Override
    public String searchProducts(String keyword, Model model){
        String path = "";
        if(isKeywordValid(keyword)){
            model.addAttribute("msg","Please enter a keyword to search for, then press the search button.");
            path = "common/error";
        } else {
            List<Product> productList = catalogRepository.searchProductList(keyword);
            List<ProductDTO> processedProductList
                    = productMapper.toProductDTOList(Product.processProductDescription(productList));
            model.addAttribute("productList", processedProductList);
            path = "catalog/search_products";
        }
        return path;
    }

    private boolean isKeywordValid(String keyword){
        return Validator.getSoleInstance().isNull(keyword) || Validator.getSoleInstance().isLessThan(keyword.length(), 1);
    }


}
