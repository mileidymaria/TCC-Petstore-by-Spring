package org.csu.mypetstore.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = -7492639752670189553L;

    @JsonProperty
    private String productId;

    @JsonProperty
    private String categoryId;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String descriptionImage;

    @JsonProperty
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