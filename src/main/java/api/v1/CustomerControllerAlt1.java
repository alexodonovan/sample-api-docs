package api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import domain.Address;
import domain.Name;
import domain.SSCN;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/customer", produces = { "application/json" })
@Api("Customer controlller - alternative options for editing details")
public class CustomerControllerAlt1 {

	@ApiOperation(value = "Update a customers address", notes = "An alternative method for more fine grained updates to a customer address details")
	@RequestMapping(value = "address/{sscn}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void address(@PathVariable(value = "sscn") String sscn, @RequestBody Address newAddress) {
		// Update address
	}

	@ApiOperation(value = "Update a customers name", notes = "An alternative method for more fine grained updates to a customer name details")
	@RequestMapping(value = "name/{sscn}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void name(@PathVariable(value = "sscn") SSCN sscn, @RequestBody Name newName) {
		// Update name
	}

}