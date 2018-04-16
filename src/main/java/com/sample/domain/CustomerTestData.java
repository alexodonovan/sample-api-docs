package com.sample.domain;

import java.util.Date;

public class CustomerTestData {

	public static Customer customer() {
		return new Customer(address(), name(), "2835340J", 52345690L,
				new ContactDetails("test@test.com", "01 12365478"), new Date());
	}

	public static Address address() {
		return new Address("11", "test street", "Dublin", "DB", "V92AEA9");
	}

	public static Name name() {
		return new Name("Mr", "Gerry", "M", "O'Connor");
	}
}
