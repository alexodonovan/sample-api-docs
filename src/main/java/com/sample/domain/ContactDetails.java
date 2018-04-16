package com.sample.domain;

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

}
