package api.v1;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import domain.Customer;
import domain.CustomerTestData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/customer", produces = { "application/json" }, method = RequestMethod.GET)
@Api(value = "ViewSCDCustomer", description = "Get the details of a customer in the SCD")
class ViewController {

    @ApiOperation(value = "View a customer's details via SSCN", response = Customer.class)
    @RequestMapping(value = "{sscn}")
    @ResponseBody
    public Customer sscn(@PathVariable(value = "sscn") String sscn) {
        return CustomerTestData.customer();
    }

    @ApiOperation(value = "View a customer's details via PPSN", response = Customer.class)
    @RequestMapping(value = "ppsn/{ppsn}")
    @ResponseBody
    public Customer ppsn(@PathVariable(value = "ppsn") String ppsn) {
        return CustomerTestData.customer();
    }

}