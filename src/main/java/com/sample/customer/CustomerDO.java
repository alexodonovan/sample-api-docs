package com.sample.customer;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.sample.domain.Address;
import com.sample.domain.ContactDetails;
import com.sample.domain.Customer;
import com.sample.domain.Name;

@Entity
@Table(name = "STATE_SAVING_CUSTOMER")
public class CustomerDO implements Serializable {

    private static final long serialVersionUID = 2628568688296055596L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "object_id", unique = true)
    private Long id;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "validation_date")
    private Date validationDate;

    @Column(name = "source_object_id")
    private Long sourceId;

    @Column(name = "status", length = 16)
    private String status;

    @Column(name = "pps_number")
    private String ppsNumber;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Embedded
    private AddressDO address;

    @Embedded
    private NameDO name;

    @Column(name = "phone_number", length = 25)
    private String phoneNumber;

    @Column(name = "email_address", length = 50)
    private String emailAddress;

    @Column(name = "SCD_DB_FLAG")
    private String scdDbFlag;

    @Column(name = "SOURCE_SYSTEM")
    private String sourceSystem;

    @Column(name = "SSCN_NUMBER", length = 12)
    private Long sscn;

    public CustomerDO() {
        // default constructor for hibernate
        timestamp = new Date();
        validationDate = new Date();
        scdDbFlag = "Y";
        sourceSystem = "PRIME";
        status = "test";

        sourceId = 3L; // what's PB here?
    }

    public static CustomerDO create(Long sscn, Name name, Address address, String ppsn, ContactDetails contactDetails,
            Date dateOfBirth) {
        CustomerDO entity = new CustomerDO();
        entity.setSscn(sscn);
        entity.setName(name);
        entity.setAddress(address);
        entity.setPpsNumber(ppsn);
        entity.setContactDetails(contactDetails);
        entity.setDateOfBirth(dateOfBirth);
        entity.isValidNewCustomer();
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScdDbFlag() {
        return scdDbFlag;
    }

    public void setScdDbFlag(String scdDbFlag) {
        this.scdDbFlag = scdDbFlag;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPpsNumber() {
        return ppsNumber;
    }

    public void setPpsNumber(String ppsNumber) {
        this.ppsNumber = ppsNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public NameDO getName() {
        return name;
    }

    public void setName(NameDO name) {
        this.name = name;
    }

    public AddressDO getAddress() {
        return address;
    }

    public void setAddress(AddressDO address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getSscn() {
        return sscn;
    }

    public void setSscn(Long sscn) {
        this.sscn = sscn;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CustomerDO)) return false;

        CustomerDO rhs = (CustomerDO) obj;
        return new EqualsBuilder().append(name, rhs.name)
                                  .append(address, rhs.address)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name)
                                    .append(address)
                                    .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(id)
                                                                          .append(name)
                                                                          .append(address)
                                                                          .toString();
    }

    @Transient
    public void setName(Name name) {
        Objects.requireNonNull(name);
        this.name = NameDO.create(name);
    }

    @Transient
    public void setAddress(Address address) {
        this.address = AddressDO.create(address.getAddressLine1(), address.getAddressLine2(), address.getCity(),
                                        address.getCounty(), address.getPostCode());
    }

    @Transient
    public void setContactDetails(ContactDetails contactDetails) {
        if (null == contactDetails) return;
        emailAddress = contactDetails.getEmailAddress();
        phoneNumber = contactDetails.getPhoneNumber();
    }

    @Transient
    public Customer toDTO() {
        Customer dto = Customer.empty();
        dto.setAddress(address.toDTO());
        dto.setName(name.toDTO());
        dto.setContactDetails(new ContactDetails(emailAddress, phoneNumber));
        dto.setPpsn(ppsNumber);
        dto.setDateOfBirth(getDateOfBirth());
        dto.setSscn(getSscn());
        return dto;
    }

    private void isValidNewCustomer() {
        // name and address checked while setting
        Objects.requireNonNull(getSscn());
        Objects.requireNonNull(getPpsNumber());
    }

}
