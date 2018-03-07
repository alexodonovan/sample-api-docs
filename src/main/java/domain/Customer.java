package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Customer {

	private Address address;
	private Name name;
	private PPSN ppsn;
	private SSCN sscn;

	public Customer() {
	}

	public Customer(Address address, Name name, PPSN ppsn, SSCN sscn) {
		this.address = address;
		this.name = name;
		this.ppsn = ppsn;
		this.sscn = sscn;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public void setPpsn(PPSN ppsn) {
		this.ppsn = ppsn;
	}

	public void setSscn(SSCN sscn) {
		this.sscn = sscn;
	}

	public Address getAddress() {
		return address;
	}

	public Name getName() {
		return name;
	}

	public PPSN getPpsn() {
		return ppsn;
	}

	public SSCN getSscn() {
		return sscn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Customer customer = (Customer) o;

		return new EqualsBuilder().append(address, customer.address).append(name, customer.name)
				.append(ppsn, customer.ppsn).append(sscn, customer.sscn).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(address).append(name).append(ppsn).append(sscn).toHashCode();
	}
}
