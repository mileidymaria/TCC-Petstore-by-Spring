package org.csu.mypetstore.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.csu.mypetstore.utils.Validator;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 8751282105532159742L;
    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private String email;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String status;

    @JsonProperty
    private String address1;

    @JsonProperty
    private String address2;

    @JsonProperty
    private String city;

    @JsonProperty
    private String state;

    @JsonProperty
    private String zip;

    @JsonProperty
    private String country;

    @JsonProperty
    private String phone;

    @JsonProperty
    private String favouriteCategoryId;

    @JsonProperty
    private String languagePreference;

    @JsonProperty
    private boolean listOption;

    @JsonProperty
    private boolean bannerOption;

    @JsonProperty
    private String bannerName;

    public Account() {
    }

    public Account(String username,
                   String password,
                   String email,
                   String firstName,
                   String lastName,
                   String status,
                   String address1,
                   String address2,
                   String city,
                   String state,
                   String zip,
                   String country,
                   String phone,
                   String favouriteCategoryId,
                   String languagePreference,
                   boolean listOption,
                   boolean bannerOption,
                   String bannerName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.phone = phone;
        this.favouriteCategoryId = favouriteCategoryId;
        this.languagePreference = languagePreference;
        this.listOption = listOption;
        this.bannerOption = bannerOption;
        this.bannerName = bannerName;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isPasswordValid(String repeatedPassword){
        return Validator.getSoleInstance().isNull(repeatedPassword)
                || Validator.getSoleInstance().isLengthEqualTo(repeatedPassword, 0);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStatus() {
        return status;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getFavouriteCategoryId() {
        return favouriteCategoryId;
    }

    public String getLanguagePreference() {
        return languagePreference;
    }

    public boolean isListOption() {
        return listOption;
    }

    public boolean isBannerOption() {
        return bannerOption;
    }

    public String getBannerName() {
        return bannerName;
    }
}