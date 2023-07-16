package org.csu.mypetstore.domain;

import org.csu.mypetstore.utils.Validator;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 6620528781626504362L;
    private Item item;
    private int quantity;
    private boolean inStock;
    private BigDecimal total;

    public CartItem(Item item, int quantity, boolean inStock) {
        this.item = item;
        this.quantity = quantity;
        this.inStock = inStock;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
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

    public void incrementQuantity() {
        quantity++;
        calculateTotal();
    }

    private void calculateTotal() {
        total = Validator.getSoleInstance().isNull(item)
                && !Validator.getSoleInstance().isNull(item.getListPrice()) ?
                item.getListPrice().multiply(new BigDecimal(quantity)) : null;
    }

}
