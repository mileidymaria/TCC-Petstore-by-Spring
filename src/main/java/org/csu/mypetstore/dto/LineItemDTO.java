package org.csu.mypetstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class LineItemDTO {
    @JsonProperty
    private int orderId;

    @JsonProperty
    private int lineNumber;

    @JsonProperty
    private int quantity;

    @JsonProperty
    private String itemId;

    @JsonProperty
    private BigDecimal unitPrice;

    @JsonProperty
    private ItemDTO item;

    @JsonProperty
    private BigDecimal total;

    public LineItemDTO() {
    }

    public LineItemDTO(int orderId, int lineNumber, int quantity, String itemId, BigDecimal unitPrice, ItemDTO item, BigDecimal total) {
        this.orderId = orderId;
        this.lineNumber = lineNumber;
        this.quantity = quantity;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.item = item;
        this.total = total;
    }


    public int getOrderId() {
        return orderId;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public ItemDTO getItem() {
        return item;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
