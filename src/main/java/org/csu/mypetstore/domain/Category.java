package org.csu.mypetstore.domain;

import java.io.Serializable;

public class Category implements Serializable {

    private static final long serialVersionUID = 3992469837058393712L;

    private String categoryId;
    private String name;
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