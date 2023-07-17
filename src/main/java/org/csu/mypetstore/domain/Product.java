package org.csu.mypetstore.domain;

import java.io.Serializable;

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