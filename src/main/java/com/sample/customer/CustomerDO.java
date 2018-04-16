package com.sample.customer;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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

	private Long id;

	private Date timestamp;
	private Date validationDate;

	private Long sourceId;

	private String status;
	private String ppsNumber;

	private Date dateOfBirth;

	private String title;
	private String firstName;
	private String middleName;
	private String surname;

	private AddressDO address;

	private String phoneNumber;
	private String emailAddress;

	private String scdDbFlag;

	private String sourceSystem;

	public CustomerDO() {
		// default constructor for hibernate
		address = new AddressDO();
		timestamp = new Date();
		validationDate = new Date();
		scdDbFlag = "Y";
		sourceSystem = "PRIME";
		status = "test";

		sourceId = 3L; // what's PB here?
	}

	@Id
	@Column(name = "object_id", unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SCD_DB_FLAG")
	public String getScdDbFlag() {
		return scdDbFlag;
	}

	public void setScdDbFlag(String scdDbFlag) {
		this.scdDbFlag = scdDbFlag;
	}

	@Column(name = "SOURCE_SYSTEM")
	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	@Column(name = "timestamp")
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "validation_date")
	public Date getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(Date validationDate) {
		this.validationDate = validationDate;
	}

	@Column(name = "source_object_id")
	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "pps_number")
	public String getPpsNumber() {
		return ppsNumber;
	}

	public void setPpsNumber(String ppsNumber) {
		this.ppsNumber = ppsNumber;
	}

	@Column(name = "date_of_birth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "middle_name")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "surname")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Embedded
	public AddressDO getAddress() {
		return address;
	}

	public void setAddress(AddressDO address) {
		this.address = address;
	}

	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "email_address")
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(id).append(title).append(firstName)
				.append(middleName).append(surname).append(address).toString();
	}

	@Transient
	public void setName(Name name) {
		Objects.requireNonNull(name);
		title = name.getTitle();
		firstName = name.getFirstName();
		middleName = name.getMiddleName();
		surname = name.getSurname();
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
		Customer dto = new Customer();
		dto.setAddress(address.toDTO());
		dto.setName(new Name(title, firstName, middleName, surname));
		dto.setContactDetails(new ContactDetails(emailAddress, phoneNumber));
		dto.setPpsn(ppsNumber);
		dto.setSscn(getId());
		dto.setDateOfBirth(getDateOfBirth());
		return dto;
	}

}
