package org.csu.mypetstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {
    @JsonProperty
    private int orderId;

    @JsonProperty
    private Date orderDate;

    @JsonProperty
    private AccountDTO accountDTO;

    @JsonProperty
    private String creditCard;

    @JsonProperty
    private String expiryDate;

    @JsonProperty
    private String cardType;

    @JsonProperty
    private String locale;

    @JsonProperty
    private String status;

    @JsonProperty
    private String courier;

    @JsonProperty
    private BigDecimal totalPrice;

    @JsonProperty
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