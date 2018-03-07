package api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import domain.Address;
import domain.Customer;
import domain.CustomerTestData;
import domain.Name;
import domain.SSCN;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v2/customer", produces = { "application/json" })
@Api(value = "Customer Controller")
public class CustomerController {

	@ApiOperation(value = "Add a new customer to the SCD")
	@RequestMapping(value = "{sscn}", method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.CREATED, reason = "The new customer was sucessfully created")
	public void newCustomer(@PathVariable(value = "sscn") String sscn, @RequestBody Customer customer) {
		// Insert customer
	}

	@ApiOperation(value = "Update a customer", notes = "Edit some or all of the customer's details. This represents a simple approach to enabling editing of customer information in the SCD. Name and address fields are necessarily editable due through change of name and change of address process in PB backoffice and presumably through in the SS products also. Editting a customer's PPPSN is also required to resolve errors in updates to PPSN.\n"
			+ "For discussion, edit a customer's SSCN?")
	@RequestMapping(value = "/{sscn}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable(value = "sscn") String sscn, @RequestBody Customer customer) {
		// Update customer
	}

	@ApiOperation(value = "Update a customers address")
	@RequestMapping(value = "address/{sscn}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void address(@PathVariable(value = "sscn") String sscn, @RequestBody Address newAddress) {
		// Update address
	}

	@ApiOperation(value = "Update a customers name")
	@RequestMapping(value = "name/{sscn}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void name(@PathVariable(value = "sscn") SSCN sscn, @RequestBody Name newName) {
		// Update name
	}

	@ApiOperation(value = "View a customer's details via SSCN", response = Customer.class)
	@RequestMapping(value = "{sscn}", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public Customer sscn(@PathVariable(value = "sscn") String sscn) {
		return CustomerTestData.customer();
	}

	@ApiOperation(value = "View a customer's details via SSCN - versioned content negotiation", response = Customer.class)
	@RequestMapping(value = "{sscn}", method = RequestMethod.GET, produces = { "application/scd.api-v3+json" })
	@ResponseBody
	public Customer sscn2(@PathVariable(value = "sscn") String sscn) {
		return CustomerTestData.customer();
	}

	@ApiOperation(value = "View a customer's details via PPSN", response = Customer.class)
	@RequestMapping(value = "ppsn/{ppsn}", method = RequestMethod.GET)
	@ResponseBody
	public Customer ppsn(@PathVariable(value = "ppsn") String ppsn) {
		return CustomerTestData.customer();
	}

}