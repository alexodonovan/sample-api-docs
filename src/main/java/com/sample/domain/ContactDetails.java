package com.sample.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonPropertyOrder({ "emailAddress", "phoneNumber" })
public class ContactDetails {

    private String emailAddress;
    private String phoneNumber;

    public ContactDetails() {
    }

    public ContactDetails(String emailAddress, String phoneNumber) {
        super();
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("phoneNumber", phoneNumber)
                .append("emailAddress", emailAddress)
                .toString();
    }
}
