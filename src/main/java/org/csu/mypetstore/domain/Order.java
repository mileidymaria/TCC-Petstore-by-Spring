package org.csu.mypetstore.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Component
public class Order {
    
    private int orderId;

    
    private Date orderDate;

    
    private Account account;

    
    private String creditCard;

    
    private String expiryDate;

    
    private String cardType;

    
    private String locale;

    
    private String status;

    
    private String courier;

    
    private BigDecimal totalPrice;

    
    private List<LineItem> lineItems = new ArrayList<>();

    public Order() {
    }

    public Order(
            int orderId,
            Date orderDate,
            Account account,
            String creditCard,
            String expiryDate,
            String cardType,
            String locale,
            String status, String courier, BigDecimal totalPrice, List<LineItem> lineItems) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.account = account;
        this.creditCard = creditCard;
        this.expiryDate = expiryDate;
        this.cardType = cardType;
        this.locale = locale;
        this.status = status;
        this.courier = courier;
        this.totalPrice = totalPrice;
        this.lineItems = lineItems;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Account getAccount() {
        return account;
    }

    public String getCourier() {
        return courier;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCardType() {
        return cardType;
    }

    public String getLocale() {
        return locale;
    }

    public String getStatus() {
        return status;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void initOrder(Account accountObj, Cart cart){
        account = accountObj;
        orderDate = new Date();
        totalPrice = cart.getSubTotal();
        creditCard = "999 999 999 999";
        expiryDate = "04/01";
        cardType = "Visa";
        courier = "UPS";
        locale = "CA";
        status = "p";
        Iterator<CartItem> i = cart.getAllCartItems();
        while(i.hasNext()){
            CartItem cartItem = i.next();
            lineItems.add(new LineItem(lineItems.size() + 1,cartItem));
        }
    }

}