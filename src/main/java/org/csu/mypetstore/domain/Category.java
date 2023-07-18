package org.csu.mypetstore.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Category implements Serializable {

    private static final long serialVersionUID = 3992469837058393712L;

    @JsonProperty
    private String categoryId;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

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