package org.csu.mypetstore.domain;


import org.csu.mypetstore.utils.Validator;

import java.io.Serializable;
import java.math.BigDecimal;

public class LineItem implements Serializable {
    private static final long serialVersionUID = 6804536240033522156L;
    private int orderId;
    private int lineNumber;
    private int quantity;
    private String itemId;
    private BigDecimal unitPrice;
    private Item item;
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

    public void setItemId(String itemId) {
        this.itemId = itemId;
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
