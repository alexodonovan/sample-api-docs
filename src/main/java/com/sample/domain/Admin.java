package com.sample.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPpsn(String ppsn) {
        this.ppsn = ppsn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("ppsn", ppsn)
                .append("dateOfBirth", dateOfBirth)
                .toString();
    }

}
