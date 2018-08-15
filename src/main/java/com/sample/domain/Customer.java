package com.sample.domain;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonPropertyOrder({ "Id", "Name", "Address", "ContactDetails", "Admin" })
@ApiModel
public class Customer {

    @ApiModelProperty(name = "Id", notes = "The customer's allocated State Savings Customer Number (SSCN) ",
            position = 0)
    @JsonProperty("Id")
    private SSCN sscn;

    @ApiModelProperty(name = "Name",
            notes = "Name, proven at time of onboarding. Trusted as source of truth for name of customer across State Savings products.",
            position = 1)
    @JsonProperty("Name")
    private Name name;

    @ApiModelProperty(name = "Address",
            notes = "Address, proven at time of onboarding. Trusted as source of truth for address of customer across State Savings products.",
            position = 2)
    @JsonProperty("Address")
    private Address address;

    @ApiModelProperty(name = "ContactDetails", position = 3)
    @JsonProperty("ContactDetails")
    private ContactDetails contactDetails;

    @ApiModelProperty(name = "Admin", position = 4)
    @JsonProperty("Admin")
    private Admin admin;

    private Customer() {
        this.admin = new Admin();
    }

    public Customer(Address address, Name name, Admin admin, SSCN sscn, ContactDetails contactDetails) {
        super();
        this.address = address;
        this.name = name;
        this.admin = admin;
        this.sscn = sscn;
        this.contactDetails = contactDetails;
    }

    public static Customer empty() {
        return new Customer();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return null == getSscn();
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public Name getName() {
        return name;
    }

    public SSCN getSscn() {
        return sscn;
    }

    public void setSscn(SSCN sscn) {
        this.sscn = sscn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return new EqualsBuilder().append(address, customer.address)
                                  .append(name, customer.name)
                                  .append(sscn, customer.sscn)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(address)
                                          .append(name)
                                          .append(sscn)
                                          .toHashCode();
    }

    @JsonIgnore
    public boolean hasMatchingSSCN(Long sscn) {
        Objects.requireNonNull(sscn);
        return sscn.equals(Long.valueOf(getSscn().number()));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("sscn", sscn)
                .append("name", name)
                .append("address", address)
                .append("admin", admin)
                .append("contactDetails", contactDetails)
                .toString();
    }

    @JsonIgnore
    public String ppsn() {
        return getAdmin().getPpsn();
    }

    @JsonIgnore
    public void setPpsn(String ppsNumber) {
        getAdmin().setPpsn(ppsNumber);
    }

    @JsonIgnore
    public Date dateOfBirth() {
        return getAdmin().getDateOfBirth();
    }

    @JsonIgnore
    public void setDateOfBirth(Date dateOfBirth) {
        getAdmin().setDateOfBirth(dateOfBirth);
    }

    @JsonIgnore
    public void setSscn(Long sscn) {
        setSscn(new SSCN(sscn));
    }

}
