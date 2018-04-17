package com.sample.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerDO, Long> {

    CustomerDO findBySscn(Long sscn);

}
