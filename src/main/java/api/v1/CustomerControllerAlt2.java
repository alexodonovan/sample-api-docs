package api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import domain.Customer;
import domain.CustomerTestData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "v1/customer", produces = { "application/json" })
@Api("Customer controlller - alternative options for editing details")
public class CustomerControllerAlt2 {

	@ApiOperation(value = "View a customer's details via PPSN", response = Customer.class)
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Customer ppsn(
			@ApiParam(value = "A customer's PPSN", required = true) @RequestParam(value = "ppsn", defaultValue = "", required = true) String ppsn) {
		return CustomerTestData.customer();
	}

}