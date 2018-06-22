package com.sample.domain;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "Id", "Name", "Address", "ContactDetails", "Admin" })
public class Customer {

    @JsonProperty("Address")
    private Address address;

    @JsonProperty("Name")
    private Name name;

    @JsonProperty("Admin")
    private Admin admin;

    @JsonProperty("Id")
    private SSCN sscn;

    @JsonProperty("ContactDetails")
    private ContactDetails contactDetails;

    private Customer() {
    }

    public Customer(Address address, Name name, Admin admin, SSCN sscn, ContactDetails contactDetails) {
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
        return sscn.equals(getSscn());
    }

    @JsonIgnore
    public String ppsn() {
        return getAdmin().getPpsn();
    }

    public void setPpsn(String ppsNumber) {
        getAdmin().setAdmin(ppsNumber);
    }

    @JsonIgnore
    public Date dateOfBirth() {
        return getAdmin().getDateOfBirth();
    }

    public void setDateOfBirth(Date dateOfBirth) {
        getAdmin().setDateOfBirth(dateOfBirth);
    }

    public void setSscn(Long sscn) {
        setSscn(new SSCN(sscn));
    }

}
