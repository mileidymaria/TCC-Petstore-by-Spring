package org.csu.mypetstore.parser;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.dto.CategoryDTO;
import org.csu.mypetstore.dto.ItemDTO;
import org.csu.mypetstore.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductParser {
    public List<ProductDTO> toProductDTOList(List<Product> productList){
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product product : productList){
            productDTOList.add(toProductDTO(product));
        }
        return productDTOList;
    }

    public ProductDTO toProductDTO(Product product){
        return new ProductDTO(
                product.getProductId(),
                product.getCategoryId(),
                product.getName(),
                product.getDescription(),
                product.getDescriptionImage(),
                product.getDescriptionText()
        );
    }

    public Product toProduct(ProductDTO product){
        return new Product(
                product.getProductId(),
                product.getCategoryId(),
                product.getName(),
                product.getDescription(),
                product.getDescriptionImage(),
                product.getDescriptionText()
        );
    }

    public List<ItemDTO> toItemDTOList (List<Item> itemList){
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : itemList){
            itemDTOList.add(toItemDTO(item));
        }
        return itemDTOList;
    }

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

    public CategoryDTO toCategoryDTO(Category category) {
        return new CategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription()
        );
    }
}
