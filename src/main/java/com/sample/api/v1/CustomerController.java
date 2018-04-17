package com.sample.api.v1;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.customer.CustomerService;
import com.sample.domain.Customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/customer", produces = { "application/json" })
@Api
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "Add a new customer to the SCD")
    @RequestMapping(value = "{sscn}", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.CREATED, reason = "The new customer was sucessfully created")
    public void newCustomer(@PathVariable(value = "sscn") Long sscn, @RequestBody Customer customer) {
        requireNonNull(customer);
        checkState(customer.hasMatchingSSCN(sscn));
        customerService.createNew(sscn, customer.getPpsn(), customer.getName(), customer.getAddress(),
                                  customer.getContactDetails(), customer.getDateOfBirth());
    }

    @ApiOperation(value = "Update a customer",
            notes = "Edit some or all of the customer's details. This represents a simple approach to enabling editing of customer information in the SCD. Name and address fields are necessarily editable due through change of name and change of address process in PB backoffice and presumably through in the SS products also. Editting a customer's PPPSN is also required to resolve errors in updates to PPSN.\n"
                    + "For discussion, edit a customer's SSCN?")
    @RequestMapping(value = "/{sscn}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable(value = "sscn") Long sscn, @RequestBody Customer customer) {
        requireNonNull(sscn);
        requireNonNull(customer);
        checkState(customer.hasMatchingSSCN(sscn));
        customerService.update(sscn, customer.getPpsn(), customer.getName(), customer.getAddress(),
                               customer.getContactDetails(), customer.getDateOfBirth());
    }

    @ApiOperation(value = "View a customer's details via SSCN", response = Customer.class)
    @RequestMapping(value = "{sscn}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> sscn(@PathVariable(value = "sscn") Long sscn) {
        Customer customer = customerService.findBySSCN(sscn);
        BodyBuilder ok = ResponseEntity.ok();
        HeadersBuilder<?> notFound = ResponseEntity.notFound();
        return customer.isEmpty() ? notFound.build() : ok.body(customer);
    }

    @ApiOperation(value = "Delete a customer from the SCD")
    @RequestMapping(value = "{sscn}", method = RequestMethod.DELETE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "The customer was successfully deleted")
    public void delete(@PathVariable(value = "sscn") Long sscn) {
        customerService.delete(sscn);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Error updating SCD: Invalid request")
    @ExceptionHandler(JpaSystemException.class)
    public void error() {
        // do nothing
    }

}