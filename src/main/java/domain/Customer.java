package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Customer {

    private Address address;
    private Name name;
    private String ppsn;
    private String sscn;

    private ContactDetails contactDetails;

    public Customer() {
    }

    public Customer(Address address, Name name, String ppsn, String sscn, ContactDetails contactDetails) {
        this.address = address;
        this.name = name;
        this.ppsn = ppsn;
        this.sscn = sscn;
        this.contactDetails = contactDetails;
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

    public void setPpsn(String ppsn) {
        this.ppsn = ppsn;
    }

    public String getSscn() {
        return sscn;
    }

    public void setSscn(String sscn) {
        this.sscn = sscn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return new EqualsBuilder().append(address, customer.address)
                                  .append(name, customer.name)
                                  .append(ppsn, customer.ppsn)
                                  .append(sscn, customer.sscn)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(address)
                                          .append(name)
                                          .append(ppsn)
                                          .append(sscn)
                                          .toHashCode();
    }
}
