package com.sample.customer;

import java.util.Date;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.domain.Address;
import com.sample.domain.ContactDetails;
import com.sample.domain.Customer;
import com.sample.domain.Name;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void createNew(Long sscn, String ppsn, Name name, Address address, ContactDetails contactDetails,
            Date dateOfBirth) {
        customerRepository.save(CustomerDO.create(sscn, name, address, ppsn, contactDetails, dateOfBirth));
    }

    public Customer findBySSCN(Long sscn) {
        Objects.requireNonNull(sscn);
        CustomerDO entity = customerRepository.findBySscn(sscn);
        return null == entity ? Customer.empty() : entity.toDTO();
    }

    public void delete(Long sscn) {
        Objects.requireNonNull(sscn);
        customerRepository.delete(sscn);
    }

    @Transactional
    public void update(Long sscn, String ppsn, Name name, Address address, ContactDetails contactDetails,
            Date dateOfBirth) {
        Objects.requireNonNull(sscn);
        CustomerDO entity = customerRepository.findBySscn(sscn);
        entity.setAddress(address);
        entity.setName(name);
        entity.setContactDetails(contactDetails);
        entity.setDateOfBirth(dateOfBirth);
        customerRepository.save(entity);
    }

}
