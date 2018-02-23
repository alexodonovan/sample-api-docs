package api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import domain.Customer;
import domain.SSCN;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/customer", produces = { "application/json" }, method = RequestMethod.PUT)
@Api(value = "InsertSCDCustomer", description = "Insert a new customer into the SCD")
class InsertController {

    @ApiOperation(value = "Add a new customer to the SCD")
    @RequestMapping(value = "{sscn}")
    @ResponseStatus(HttpStatus.CREATED)
    public void newCustomer(@PathVariable(value = "sscn") SSCN sscn, @RequestBody Customer customer) {
        // Insert customer
    }

}