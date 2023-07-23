package org.csu.mypetstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO{

    
    private String productId;

    
    private String categoryId;

    
    private String name;

    
    private String description;

    
    private String descriptionImage;

    
    private String descriptionText;
    public ProductDTO() {
    }

    public ProductDTO(String productId, String categoryId, String name, String description, String descriptionImage, String descriptionText) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.descriptionImage = descriptionImage;
        this.descriptionText = descriptionText;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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
}