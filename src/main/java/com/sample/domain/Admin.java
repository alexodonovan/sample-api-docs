package com.sample.domain;

import java.util.Date;

public class Admin {

    private String ppsn;
    private Date dateOfBirth;

    public Admin() {
        // default constructor
    }

    public Admin(String ppsn, Date dateOfBirth) {
        this.ppsn = ppsn;
        this.dateOfBirth = dateOfBirth;
    }

    public String getPpsn() {
        return ppsn;
    }

    public void setAdmin(String ppsn) {
        this.ppsn = ppsn;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPpsn(String ppsn) {
        this.ppsn = ppsn;
    }

}
