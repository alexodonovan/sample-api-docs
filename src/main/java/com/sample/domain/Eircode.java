package com.sample.domain;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Eircode {
    private static final int EIRCODE_LENGTH = 7;
    private static final String EIRCODE_REGEX = "^([AC-FHKNPRTV-Y]\\d{2}|D6W)[0-9AC-FHKNPRTV-Y]{4}$";
    protected String routingKey;
    protected String identifier;

    protected Eircode() {
    }

    private Eircode(String routingKey, String identifier) {
        this.routingKey = routingKey;
        this.identifier = identifier;
    }

    public static Eircode fromString(String input) {
        if (StringUtils.isEmpty(input)) return new UnknownEircode();

        String parsed = input.toUpperCase()
                             .replaceAll("[^A-Z0-9]", "");

        if (StringUtils.isEmpty(parsed) || parsed.length() != EIRCODE_LENGTH) return new UnknownEircode();

        Eircode eircodeToValidate = new Eircode(parsed.substring(0, 3), parsed.substring(3));
        return isValid(eircodeToValidate) ? eircodeToValidate : new UnknownEircode();
    }

    public static boolean isValid(Eircode input) {
        return Pattern.compile(EIRCODE_REGEX)
                      .matcher(input.routingKey + input.identifier)
                      .matches();
    }

    public boolean exists() {
        return true;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String unformattedValue() {
        return routingKey + identifier;
    }

    public String formattedValue() {
        return (routingKey + " " + identifier).trim();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(routingKey)
                                                                          .append(identifier)
                                                                          .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Eircode)) return false;

        Eircode eircode = (Eircode) o;

        return new EqualsBuilder().append(routingKey, eircode.routingKey)
                                  .append(identifier, eircode.identifier)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(routingKey)
                                          .append(identifier)
                                          .toHashCode();
    }
}
