package org.csu.mypetstore.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.csu.mypetstore.dto.ProductDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {

    private static final long serialVersionUID = -7492639752670189553L;

    
    private String productId;

    
    private String categoryId;

    
    private String name;

    
    private String description;

    
    private String descriptionImage;

    
    private String descriptionText;

    public Product() {
    }

    public Product(String productId, String categoryId, String name, String description, String descriptionImage, String descriptionText) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.descriptionImage = descriptionImage;
        this.descriptionText = descriptionText;
    }

    public void setDescriptionImage(String descriptionImage) {
        this.descriptionImage = descriptionImage;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public String getProductId() {
        return productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionImage() {
        return descriptionImage;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public static Product processProductDescription(Product product){
        String [] temp = product.getDescription().split("\"");
        product.setDescriptionImage(temp[1]);
        product.setDescriptionText(temp[2].substring(1));
        return product;
    }

    public static List<Product> processProductDescription(List<Product> productList){
        List<Product> processedProducts = new ArrayList<>();
        for(Product product : productList) {
            processedProducts.add(processProductDescription(product));
        }
        return processedProducts;
    }
}