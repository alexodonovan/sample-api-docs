package com.sample.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SSCN {

    @ApiModelProperty(notes = "The customer's allocated State Savings Customer Number (SSCN) ", position = 0)
    private String sscn;

    public SSCN() {
        // default constructor
    }

    protected SSCN(String sscn) {
        checkNotNull(sscn);
        this.sscn = sscn;
    }

    public SSCN(Long sscn) {
        this(sscn.toString());
    }

    public String number() {
        return sscn;
    }

    @JsonProperty("sscn")
    public String getSscn() {
        return sscn;
    }

    public void setSscn(String sscn) {
        this.sscn = sscn;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof SSCN)) return false;

        SSCN rhs = (SSCN) obj;

        return new EqualsBuilder().append(sscn, rhs.sscn)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 17).append(sscn)
                                          .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("sscn", sscn)
                                                                          .toString();
    }
}
