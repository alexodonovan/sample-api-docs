package com.sample.api.v1;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import com.sample.customer.CustomerService;
import com.sample.domain.Customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/customer", produces = { "application/json" })
@Api
public class CustomerController {

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "Add a new customer to the SCD")
    @RequestMapping(value = "{sscn}", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.CREATED, reason = "The new customer was successfully created")
    public void createCustomer(@PathVariable(value = "sscn") Long sscn, @RequestBody Customer customer) {
        logger.info("#####put request {} with {}", sscn, customer);
        requireNonNull(customer);
        checkState(customer.hasMatchingSSCN(sscn));
        customerService.createNew(
                sscn,
                customer.ppsn(),
                customer.getName(),
                customer.getAddress(),
                customer.getContactDetails(),
                customer.dateOfBirth()
        );
    }

    @ApiOperation(value = "Update a customer",
            notes = "Edit some or all of the customer's details. This represents a simple approach to enabling editing "
                    + "of customer information in the SCD. Name and address fields are necessarily editable due through "
                    + "change of name and change of address process in PB back office and presumably through in the SS "
                    + "products also. Editing a customer's PPSN is also required to resolve errors in updates to PPSN.\n"
                    + "For discussion, edit a customer's SSCN?")
    @RequestMapping(value = "/{sscn}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(@PathVariable(value = "sscn") Long sscn, @RequestBody Customer customer) {
        logger.info("#####post request {} with {}", sscn, customer);
        requireNonNull(sscn);
        requireNonNull(customer);
        checkState(customer.hasMatchingSSCN(sscn));
        customerService.update(
                sscn,
                customer.ppsn(),
                customer.getName(),
                customer.getAddress(),
                customer.getContactDetails(),
                customer.dateOfBirth()
        );
    }

    @ApiOperation(value = "View a customer's details via SSCN", response = Customer.class)
    @RequestMapping(value = "{sscn}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> retrieveCustomer(@PathVariable(value = "sscn") Long sscn) {
        Customer customer = customerService.findBySSCN(sscn);
        logger.info("#####get request {} with {}", sscn, customer);
        BodyBuilder ok = ResponseEntity.ok();
        HeadersBuilder<?> notFound = ResponseEntity.notFound();
        return customer.isEmpty() ? notFound.build() : ok.body(customer);
    }

    @ApiOperation(value = "Delete a customer from the SCD")
    @RequestMapping(value = "{sscn}", method = RequestMethod.DELETE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "The customer was successfully deleted")
    public void deleteCustomer(@PathVariable(value = "sscn") Long sscn) {
        logger.info("#####delete request with {}", sscn);
        customerService.delete(sscn);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Error updating SCD: Invalid request")
    @ExceptionHandler(JpaSystemException.class)
    public void error() {
        logger.info("#####error");
        // do nothing
    }

}