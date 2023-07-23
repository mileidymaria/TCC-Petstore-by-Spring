package org.csu.mypetstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {
    
    private int orderId;

    
    private Date orderDate;

    
    private AccountDTO accountDTO;

    
    private String creditCard;

    
    private String expiryDate;

    
    private String cardType;

    
    private String locale;

    
    private String status;

    
    private String courier;

    
    private BigDecimal totalPrice;

    
    private List<LineItemDTO> lineItems = new ArrayList<>();

    public OrderDTO(int orderId, Date orderDate, AccountDTO accountDTO, String creditCard, String expiryDate, String cardType, String locale, String status, String courier, BigDecimal totalPrice, List<LineItemDTO> lineItems) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.accountDTO = accountDTO;
        this.creditCard = creditCard;
        this.expiryDate = expiryDate;
        this.cardType = cardType;
        this.locale = locale;
        this.status = status;
        this.courier = courier;
        this.totalPrice = totalPrice;
        this.lineItems = lineItems;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setLineItems(List<LineItemDTO> lineItems) {
        this.lineItems = lineItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getCourier() {
        return courier;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
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

    public List<LineItemDTO> getLineItems() {
        return lineItems;
    }
}