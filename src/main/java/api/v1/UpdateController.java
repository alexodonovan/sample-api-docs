package api.v1;

import domain.Address;
import domain.Customer;
import domain.Name;
import domain.SSCN;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/customer", produces = { "application/json" }, method = RequestMethod.POST)
@Api(value = "UpdateSCDCustomer", description = "Update a customer's details in the SCD")
class UpdateController {


    @ApiOperation(value = "Update a customer")
    @RequestMapping(value = "/{sscn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(value = "sscn") SSCN sscn, @RequestBody Customer customer) {
        // Update customer
    }

    @ApiOperation(value = "Update a customers address")
    @RequestMapping(value = "address/{sscn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void address(@PathVariable(value = "sscn") SSCN sscn, @RequestBody Address newAddress) {
        // Update address
    }

    @ApiOperation(value = "Update a customers name")
    @RequestMapping(value = "name/{sscn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void name(@PathVariable(value = "sscn") SSCN sscn, @RequestBody Name newName) {
        // Update name
    }


}