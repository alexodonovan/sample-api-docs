package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Name {

	private String prefix;
	private String forename;
	private String forename2;
	private String forename3;
	private String surname;
	private String suffix;

	public Name() {
	}

	public Name(String prefix, String forename, String forename2, String forename3, String surname, String suffix) {
		this.prefix = prefix;
		this.forename = forename;
		this.forename2 = forename2;
		this.forename3 = forename3;
		this.surname = surname;
		this.suffix = suffix;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getForename() {
		return forename;
	}

	public String getForename2() {
		return forename2;
	}

	public String getForename3() {
		return forename3;
	}

	public String getSurname() {
		return surname;
	}

	public String getSuffix() {
		return suffix;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Name name = (Name) o;

		return new EqualsBuilder().append(prefix, name.prefix).append(forename, name.forename)
				.append(forename2, name.forename2).append(forename3, name.forename3).append(surname, name.surname)
				.append(suffix, name.suffix).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(prefix).append(forename).append(forename2).append(forename3)
				.append(surname).append(suffix).toHashCode();
	}
}
