package com.sample.domain;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Customer {

	private Address address;
	private Name name;
	private String ppsn;
	private Long sscn;

	private ContactDetails contactDetails;

	private Date dateOfBirth;

	public Customer() {
	}

	public Customer(Address address, Name name, String ppsn, Long sscn, ContactDetails contactDetails,
			Date dateOfBirth) {
		this.address = address;
		this.name = name;
		this.ppsn = ppsn;
		this.sscn = sscn;
		this.contactDetails = contactDetails;
		this.dateOfBirth = dateOfBirth;
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

	public String getPpsn() {
		return ppsn;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setPpsn(String ppsn) {
		this.ppsn = ppsn;
	}

	public Long getSscn() {
		return sscn;
	}

	public void setSscn(Long sscn) {
		this.sscn = sscn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Customer customer = (Customer) o;

		return new EqualsBuilder().append(address, customer.address).append(name, customer.name)
				.append(ppsn, customer.ppsn).append(sscn, customer.sscn).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(address).append(name).append(ppsn).append(sscn).toHashCode();
	}

	@JsonIgnore
	public boolean hasMatchingSSCN(Long sscn) {
		Objects.requireNonNull(sscn);
		return sscn.equals(getSscn());
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
