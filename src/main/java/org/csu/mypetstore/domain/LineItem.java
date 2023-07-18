package org.csu.mypetstore.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.csu.mypetstore.utils.Validator;

import java.io.Serializable;
import java.math.BigDecimal;

public class LineItem implements Serializable {
    private static final long serialVersionUID = 6804536240033522156L;
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
    private Item item;

    @JsonProperty
    private BigDecimal total;

    public LineItem() {
    }

    public LineItem(int orderId, int lineNumber, int quantity, String itemId, BigDecimal unitPrice, Item item, BigDecimal total) {
        this.orderId = orderId;
        this.lineNumber = lineNumber;
        this.quantity = quantity;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.item = item;
        this.total = total;
    }

    public LineItem(int lineNumber, CartItem cartItem) {
        this.lineNumber = lineNumber;
        this.quantity = cartItem.getQuantity();
        this.itemId = cartItem.getItem().getItemId();
        this.unitPrice = cartItem.getItem().getListPrice();
        this.item = cartItem.getItem();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getItemId() {
        return itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        calculateTotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotal();
    }

    private void calculateTotal() {
        total = Validator.getSoleInstance().isNull(item)
                && !Validator.getSoleInstance().isNull(item.getListPrice()) ?
                item.getListPrice().multiply(new BigDecimal(quantity)) : null;
    }
}
