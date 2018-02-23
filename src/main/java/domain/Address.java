package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

    private final String addressLine1;

    private final String addressLine2;

    private final String addressLine3;

    private final String addressLine4;

    @JsonProperty("eircode")
    private Eircode eircode;

    private final County county;

    public Address(String addressLine1, String addressLine2, String addressLine3, String addressLine4, County county,
                   Eircode eircode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.addressLine4 = addressLine4;
        this.eircode = eircode;
        this.county = county;
    }

    @JsonCreator
    public Address(@JsonProperty("addressLine1") String addressLine1, @JsonProperty("addressLine2") String addressLine2,
                   @JsonProperty("addressLine3") String addressLine3, @JsonProperty("addressLine4") String addressLine4,
                   @JsonProperty("county") County county) {
        super();
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.addressLine4 = addressLine4;
        this.eircode = new UnknownEircode();
        this.county = county;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public Eircode getEircode() {
        return eircode;
    }

    public County getCounty() {
        return county;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return new EqualsBuilder()
                .append(addressLine1, address.addressLine1)
                .append(addressLine2, address.addressLine2)
                .append(addressLine3, address.addressLine3)
                .append(addressLine4, address.addressLine4)
                .append(eircode, address.eircode)
                .append(county, address.county)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(addressLine1)
                .append(addressLine2)
                .append(addressLine3)
                .append(addressLine4)
                .append(eircode)
                .append(county)
                .toHashCode();
    }
}

