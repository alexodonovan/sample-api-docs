package domain;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PPSN {

    private final String ppsn;

    @JsonCreator
    protected PPSN(@JsonProperty("ppsn") String ppsn) {
        checkNotNull(ppsn);
        this.ppsn = ppsn;
    }

    @JsonProperty("ppsn")
    public String number() {
        return ppsn;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof PPSN)) return false;

        PPSN rhs = (PPSN) obj;

        return new EqualsBuilder().append(ppsn, rhs.ppsn)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 17).append(ppsn)
                                          .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("ppsn", ppsn)
                                                                          .toString();
    }
}
