package com.sample.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonPropertyOrder({ "firstName", "middleName", "surname", "title" })
public class Name {

    private String title;
    private String firstName;
    private String middleName;
    private String surname;

    public Name() {
    }

    public Name(String title, String firstName, String middleName, String surname) {
        super();
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("title", title)
                .append("firstName", firstName)
                .append("middleName", middleName)
                .append("surname", surname)
                .toString();
    }

}
