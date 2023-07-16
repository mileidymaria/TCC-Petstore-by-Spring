package org.csu.mypetstore.dto;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class OrderDTO {
    private int orderId;
    private String username;
    private Date orderDate;
    private String shipAddress1;
    private String shipAddress2;
    private String shipCity;
    private String shipState;
    private String shipZip;
    private String shipCountry;
    private String billAddress1;
    private String billAddress2;
    private String billCity;
    private String billState;
    private String billZip;
    private String billCountry;
    private String courier;
    private BigDecimal totalPrice;
    private String billToFirstName;
    private String billToLastName;
    private String shipToFirstName;
    private String shipToLastName;
    private String creditCard;
    private String expiryDate;
    private String cardType;
    private String locale;
    private String status;
    private List<LineItemDTO> lineItems = new ArrayList<LineItemDTO>();

    public OrderDTO() {
    }

    public OrderDTO(
            int orderId,
            String username,
            Date orderDate,
            String shipAddress1,
            String shipAddress2,
            String shipCity,
            String shipState,
            String shipZip,
            String shipCountry,
            String billAddress1,
            String billAddress2,
            String billCity,
            String billState,
            String billZip,
            String billCountry,
            String courier,
            BigDecimal totalPrice,
            String billToFirstName,
            String billToLastName,
            String shipToFirstName,
            String shipToLastName,
            String creditCard,
            String expiryDate,
            String cardType,
            String locale,
            String status,
            List<LineItemDTO> lineItems) {
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
        this.shipAddress1 = shipAddress1;
        this.shipAddress2 = shipAddress2;
        this.shipCity = shipCity;
        this.shipState = shipState;
        this.shipZip = shipZip;
        this.shipCountry = shipCountry;
        this.billAddress1 = billAddress1;
        this.billAddress2 = billAddress2;
        this.billCity = billCity;
        this.billState = billState;
        this.billZip = billZip;
        this.billCountry = billCountry;
        this.courier = courier;
        this.totalPrice = totalPrice;
        this.billToFirstName = billToFirstName;
        this.billToLastName = billToLastName;
        this.shipToFirstName = shipToFirstName;
        this.shipToLastName = shipToLastName;
        this.creditCard = creditCard;
        this.expiryDate = expiryDate;
        this.cardType = cardType;
        this.locale = locale;
        this.status = status;
        this.lineItems = lineItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getShipAddress1() {
        return shipAddress1;
    }

    public String getShipAddress2() {
        return shipAddress2;
    }

    public String getShipCity() {
        return shipCity;
    }

    public String getShipState() {
        return shipState;
    }

    public String getShipZip() {
        return shipZip;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public String getBillAddress1() {
        return billAddress1;
    }

    public String getBillAddress2() {
        return billAddress2;
    }

    public String getBillCity() {
        return billCity;
    }

    public String getBillState() {
        return billState;
    }

    public String getBillZip() {
        return billZip;
    }

    public String getBillCountry() {
        return billCountry;
    }

    public String getCourier() {
        return courier;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getBillToFirstName() {
        return billToFirstName;
    }

    public String getBillToLastName() {
        return billToLastName;
    }

    public String getShipToFirstName() {
        return shipToFirstName;
    }

    public String getShipToLastName() {
        return shipToLastName;
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