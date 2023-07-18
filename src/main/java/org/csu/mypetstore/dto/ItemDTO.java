package org.csu.mypetstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.csu.mypetstore.domain.Product;

import java.math.BigDecimal;

public class ItemDTO {

    @JsonProperty
    private String itemId;

    @JsonProperty
    private String productId;

    @JsonProperty
    private BigDecimal listPrice;

    @JsonProperty
    private BigDecimal unitCost;

    @JsonProperty
    private int supplierId;

    @JsonProperty
    private String status;

    @JsonProperty
    private String attribute1;

    @JsonProperty
    private String attribute2;

    @JsonProperty
    private String attribute3;

    @JsonProperty
    private String attribute4;

    @JsonProperty
    private String attribute5;

    @JsonProperty
    private ProductDTO product;

    @JsonProperty
    private int quantity;

    public ItemDTO() {
    }

    public ItemDTO(String itemId, String productId, BigDecimal listPrice, BigDecimal unitCost, int supplierId, String status, String attribute1, String attribute2, String attribute3, String attribute4, String attribute5, ProductDTO product, int quantity) {
        this.itemId = itemId;
        this.productId = productId;
        this.listPrice = listPrice;
        this.unitCost = unitCost;
        this.supplierId = supplierId;
        this.status = status;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
        this.product = product;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public String getProductId() {
        return productId;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getStatus() {
        return status;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
