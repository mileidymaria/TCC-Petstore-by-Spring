package org.csu.mypetstore.dto;

import java.math.BigDecimal;

public class LineItemDTO {
    private int orderId;
    private int lineNumber;
    private int quantity;
    private String itemId;
    private BigDecimal unitPrice;
    private ItemDTO item;
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
