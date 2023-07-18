package org.csu.mypetstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDTO {

    @JsonProperty
    private String categoryId;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    public CategoryDTO(String categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public CategoryDTO() {
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
}