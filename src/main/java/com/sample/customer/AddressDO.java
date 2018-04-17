package com.sample.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.sample.domain.Address;

@Embeddable
public class AddressDO implements Serializable {

    private static final long serialVersionUID = 2628568688296055596L;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String county;
    private String postcode;

    @Column(name = "address_line_1")
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Column(name = "address_line_2")
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "county")
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Column(name = "postcode")
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Transient
    public static AddressDO create(String addressLine1, String addressLine2, String city, String county,
            String postcode) {
        AddressDO addressDO = new AddressDO();
        addressDO.setAddressLine1(addressLine1);
        addressDO.setAddressLine2(addressLine2);
        addressDO.setCity(city);
        addressDO.setCounty(county);
        addressDO.setPostcode(postcode);
        return addressDO;
    }

    @Transient
    public Address toDTO() {
        return new Address(getAddressLine1(), getAddressLine2(), getCity(), getCounty(), getPostcode());
    }

}
