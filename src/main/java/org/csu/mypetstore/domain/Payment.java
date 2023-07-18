package org.csu.mypetstore.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment {
    @JsonProperty
    private String creditCard;

    @JsonProperty
    private String expiryDate;

    @JsonProperty
    private String cardType;

    @JsonProperty
    private String courier;

    @JsonProperty
    private String locale;

    public Payment() {
        creditCard = "999 999 999 999";
        expiryDate = "04/01";
        cardType = "Visa";
        courier = "UPS";
        locale = "CA";
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

    public String getCourier() {
        return courier;
    }

    public String getLocale() {
        return locale;
    }
}
