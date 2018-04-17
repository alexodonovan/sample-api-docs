package com.sample.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.sample.domain.Name;

@Embeddable
public class NameDO implements Serializable {

    private static final long serialVersionUID = 2628568688296055596L;

    @Column(name = "title")
    private String title;

    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 30)
    private String middleName;

    @Column(name = "surname", length = 30, nullable = false)
    private String surname;

    public static NameDO create(Name name) {
        NameDO entity = new NameDO();
        entity.setTitle(name.getTitle());
        entity.setFirstName(name.getFirstName());
        entity.setMiddleName(name.getMiddleName());
        entity.setSurname(name.getSurname());
        return entity;
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
    public boolean equals(Object obj) {
        if (!(obj instanceof NameDO)) return false;

        NameDO rhs = (NameDO) obj;
        return new EqualsBuilder().append(title, rhs.title)
                                  .append(firstName, rhs.firstName)
                                  .append(middleName, rhs.middleName)
                                  .append(surname, rhs.surname)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title)
                                    .append(firstName)
                                    .append(middleName)
                                    .append(surname)
                                    .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(title)
                                                                          .append(firstName)
                                                                          .append(middleName)
                                                                          .append(surname)
                                                                          .toString();
    }

    @Transient
    public Name toDTO() {
        return new Name(title, firstName, middleName, surname);
    }

}
