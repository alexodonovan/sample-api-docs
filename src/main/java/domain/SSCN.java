package domain;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SSCN {

    private final String sscn;

    @JsonCreator
    protected SSCN(@JsonProperty("sscn") String sscn) {
        checkNotNull(sscn);
        this.sscn = sscn;
    }

    @JsonProperty("sscn")
    public String number() {
        return sscn;
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
