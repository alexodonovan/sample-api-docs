package com.sample.domain;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class CustomerTest {

    private Customer sut;

    @Before
    public void setUp() {
        sut = Customer.empty();
    }

    @Test
    public void jsonSerializeTest() {
        sut.setSscn(12345678L);
        sut.setName(new Name("Mr", "Alex", "S", "O'Donovan"));
        sut.setAddress(new Address("123 Street", "Some Area", "Cork", "Co. Cork", "V92AEB2"));
        sut.setContactDetails(new ContactDetails("testemail@test.com", "01 654123"));
        sut.setAdmin(new Admin("1234567J", new Date()));
        String json = toJson(sut);
        System.out.println(json);
    }

    private String toJson(Object obj) {
        ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
