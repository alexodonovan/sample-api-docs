package domain;

public class CustomerTestData {

    public static Customer customer() {
        return new Customer(address(), name(), new PPSN("2835340"), new SSCN("12345690"));
    }

    public static Address address() {
        return new Address("11", "test street", "", "", new County("DB", "Dublin"), new UnknownEircode());
    }

    public static Name name() {
        return new Name("Mr", "Gerry", "John", "", "Mahoney", "");
    }
}
