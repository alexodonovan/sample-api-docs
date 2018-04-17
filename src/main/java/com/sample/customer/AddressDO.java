package com.sample.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.sample.domain.Address;

@Embeddable
public class AddressDO implements Serializable {

    private static final long serialVersionUID = 2628568688296055596L;

    @Column(name = "ADDRESS_LINE_1", length = 32, nullable = false)
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2", length = 32)
    private String addressLine2;

    @Column(name = "CITY", length = 32, nullable = false)
    private String city;

    @Column(name = "COUNTY", length = 35)
    private String county;

    @Column(name = "POSTCODE", length = 10)
    private String postcode;

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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AddressDO)) return false;

        AddressDO rhs = (AddressDO) obj;
        return new EqualsBuilder().append(addressLine1, rhs.addressLine1)
                                  .append(addressLine2, rhs.addressLine2)
                                  .append(city, rhs.city)
                                  .append(county, rhs.county)
                                  .append(postcode, rhs.postcode)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(addressLine1)
                                    .append(addressLine2)
                                    .append(addressLine2)
                                    .append(addressLine2)
                                    .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(addressLine1)
                                                                          .append(addressLine2)
                                                                          .append(city)
                                                                          .append(county)
                                                                          .append(postcode)
                                                                          .toString();
    }

    public Address toDTO() {
        return new Address(getAddressLine1(), getAddressLine2(), getCity(), getCounty(), getPostcode());
    }

}
