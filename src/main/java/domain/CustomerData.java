package domain;

public class CustomerData {

    public static Customer customer(String firstName, String middleName, String surname, String title,
    		                        String addressLine1, String addressLine2, String city, String county, String country, String postCode,
    		                        String emailAddress, String phoneNumber,
    		                        String ppsn, String sscn) {
        return new Customer(address(addressLine1, addressLine2, city, county, country, postCode), 
        		    name(title, firstName, middleName, surname),
        		    ppsn, sscn,
               new ContactDetails(emailAddress, phoneNumber));
    }

    public static Address address(String addressLine1, String addressLine2, String city, String county, String country, String postCode) {
        return new Address(addressLine1, addressLine2, city, county, country, postCode);
    }

    public static Name name(String title, String firstName, String middleName, String surname) {
        return new Name(title, firstName, middleName, surname);
    }
}
