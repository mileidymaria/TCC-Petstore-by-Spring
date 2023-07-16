package org.csu.mypetstore.dto;

public class CategoryDTO {

    private String categoryId;
    private String name;
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