package com.sample.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

	private String addressLine1;

	private String addressLine2;

	private String postCode;

	private String city;

	private String county;

	@JsonCreator
	public Address(@JsonProperty("addressLine1") String addressLine1, @JsonProperty("addressLine2") String addressLine2,
			@JsonProperty("city") String city, @JsonProperty("county") String county,
			@JsonProperty("postCode") String postCode) {
		super();
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.county = county;
		this.postCode = postCode;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Address address = (Address) o;

		return new EqualsBuilder().append(addressLine1, address.addressLine1).append(addressLine2, address.addressLine2)
				.append(city, address.city).append(postCode, address.postCode).append(county, address.county)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(addressLine1).append(addressLine2).append(city).append(postCode)
				.append(county).toHashCode();
	}
}
