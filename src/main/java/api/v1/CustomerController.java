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
import domain.ContactDetails;
import domain.Customer;
import domain.CustomerTestData;
import domain.CustomerData;
import domain.Name;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;
import exception.InvalidJSONStructure;
import exception.KeywordNotFoundException;
import exception.MandatoryFieldNullValueInserted;
import exception.MaximumFieldLengthExceeded;
import exception.SSCNNotInPrizeBondRange;
import exception.UniqueConstraintViolated;
import exception.WhenOthers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "v1/customer", produces = { "application/json" })
@Api
public class CustomerController {
	@ApiOperation(value = "Add a new customer to the SCD")
	@RequestMapping(value = "{sscn}", method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.CREATED, reason = "The new customer was sucessfully created")
	public void newCustomer(@PathVariable(value = "sscn") String sscn, @RequestBody Customer customer) throws SQLException {
		int retCode;
		Connection conn = null;
     try {
    	
    	// Insert customer
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:peter/peter@localhost:1521/ORA12C");
		conn = ods.getConnection();
		
		CallableStatement cstmt = conn.prepareCall("{CALL p_pb_operations.add_new_customer(?,?,?,?)}");
		String jsonDataString = "";

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonDataString = "{" +
		                     "\"address\":  "        + objectMapper.writeValueAsString(customer.getAddress())        + ", " +
		                     "\"contactDetails\":  " + objectMapper.writeValueAsString(customer.getContactDetails()) + ", " +
		                     "\"name\":  "           + objectMapper.writeValueAsString(customer.getName())           + ", " + 
		                     "\"ppsn\":  "           + objectMapper.writeValueAsString(customer.getPpsn())         + ", " +
                             "\"sscn\":  "           + objectMapper.writeValueAsString(customer.getSscn())                +
			                 "}";
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cstmt.setString(1, jsonDataString);
		
		System.out.println(jsonDataString);

		cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
		cstmt.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
		cstmt.registerOutParameter(4, oracle.jdbc.OracleTypes.CHAR);

		cstmt.executeUpdate();
		// conn.commit();
		
		cstmt.close();
		conn.close();

	} catch (SQLException e) {
    	//throw new KeywordNotFoundException("Peter Exception 404 Test");

		conn.close();
	    retCode = e.getErrorCode();
		System.out.println("**********" + e.getMessage() + " **********");
		//e.printStackTrace();
		if (retCode == 20700) {
			throw new InvalidJSONStructure("Invalid JSON Structure");
		
		} else if (retCode == 20701) {
			throw new SSCNNotInPrizeBondRange("ORA-20701: The SSCN number (250000) supplied with the JSON data does not fall within the range of SSCNs assigned to Prize Bonds");

		} else if (retCode == 20702) {
		    throw new UniqueConstraintViolated("ORA-00001: unique constraint (AML.SSC_UK) violated\nOra-06512: at AML.P_PB_OPERATIONS.ADD_NEW_CUSTOMER, line 91\nORA-06512: at line 2");

		} else if (retCode == 20703) {
		    throw new MaximumFieldLengthExceeded("ORA-01289: value too large for column\"AML\".\"AML_P_PB_OPERATIONS.ADD_CUSTOMER\".\"ADDRESS_LINE_1 (actual: 89, maximum: 32)");
		
		} else if (retCode == 20704) {
		    throw new MandatoryFieldNullValueInserted("ORA-01400: cannot insert NULL into (\"AML\".\"PB_CUSTOMER\".\"ADDRESS_LINE_1\")");
		
		} else {
		    throw new WhenOthers("XXX");
		}

//		throw new RuntimeException(e);
		}
	}

	@ApiOperation(value = "Update a customer", notes = "Edit some or all of the customer's details. This represents a simple approach to enabling editing of customer information in the SCD. Name and address fields are necessarily editable due through change of name and change of address process in PB backoffice and presumably through in the SS products also. Editting a customer's PPPSN is also required to resolve errors in updates to PPSN.\n"
			+ "For discussion, edit a customer's SSCN?")
	@RequestMapping(value = "/{sscn}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable(value = "sscn") String sscn, @RequestBody Customer customer) throws SQLException {
		int retCode;
		Connection conn = null;
    try {
		// Update customer
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:peter/peter@localhost:1521/ORA12C");
		conn = ods.getConnection();
		
		CallableStatement cstmt = conn.prepareCall("{CALL scd_operations.update_customer(?,?)}");
		Clob jsonSCDDataStruct = conn.createClob();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonDataString = "";
		try {
			jsonDataString = "{" +
		                     "\"address\":  "        + objectMapper.writeValueAsString(customer.getAddress())        + ", " +
		                     "\"contactDetails\":  " + objectMapper.writeValueAsString(customer.getContactDetails()) + ", " +
		                     "\"name\":  "           + objectMapper.writeValueAsString(customer.getName())           + ", " + 
		                     "\"ppsn\":  "           + objectMapper.writeValueAsString(customer.getPpsn())         + ", " +
                             "\"sscn\":  "           + objectMapper.writeValueAsString(customer.getSscn())                +
			                 "}";
		    // jsonDataString = objectMapper.writeValueAsString(CustomerTestData.customer());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jsonSCDDataStruct.setString(1, jsonDataString);
		
		cstmt.setLong(1, Long.parseLong(sscn));
		cstmt.setClob(2, jsonSCDDataStruct);

		cstmt.executeUpdate();
		// conn.commit();
		
		cstmt.close();
		conn.close();

	} catch (SQLException e) {
	    retCode = e.getErrorCode();
		System.err.println("Error: " + retCode + " - " + e.getMessage());
		conn.close();

		throw new RuntimeException(e);
		}
	}

	@ApiOperation(value = "View a customer's details via SSCN", response = Customer.class)
	@RequestMapping(value = "{sscn}", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public Customer sscn(@PathVariable(value = "sscn") String sscn) throws SQLException {
		int retCode;
		Connection conn = null;
    try {
		// Update customer
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:peter/peter@localhost:1521/ORA12C");
		conn = ods.getConnection();
		
		CallableStatement cstmt = conn.prepareCall("{CALL p_pb_operations.view_customer(?,?,?)}");
		
	    int[] sscnNumberList = {10000, 10001, 10002, 10003};
	    String[] ppsNumberList  = {"8965189U"};
	    
	    java.sql.Array sscnNumberArray = ((OracleConnection) conn).createOracleArray("T_NUMBER_ARRAY", sscnNumberList);
	    java.sql.Array ppsNumberArray = ((OracleConnection) conn).createOracleArray("T_VARCHAR2_ARRAY", ppsNumberList);

	    cstmt.setArray(1, sscnNumberArray);
	    cstmt.setArray(2, ppsNumberArray);
		cstmt.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
		
		cstmt.executeUpdate();
		
		ResultSet rs = cstmt.getObject(3, ResultSet.class);
		
		rs.next(); 
		rs.next(); // Simulate looping through the results set
		rs.next();
		rs.next();

		String firstName    = rs.getString(1);
	    String middleName   = rs.getString(2);
	    String surname      = rs.getString(3);
	    String title        = rs.getString(4);
	    String addressLine1 = rs.getString(5);
	    String addressLine2 = rs.getString(6);
	    String city         = rs.getString(7);
	    String country      = rs.getString(8);
	    String county       = rs.getString(9);
	    String postCode     = rs.getString(10);
	    String emailAddress = rs.getString(11);
	    String phoneNumber  = rs.getString(12);
	    String ppsn         = rs.getString(13);
	    String sscn2        = rs.getString(14);
		
		//if (rs.next())                // *** Assumption: single record required - needs to be a check for > 1 row - SSCN is not a unique key *** 
		//	throw new SQLException(); // Exact error message needs to be customised

	    cstmt.close();
		rs.close();
		conn.close();

	    return CustomerData.customer(firstName, middleName, surname, title,
	    		                     addressLine1, addressLine2, city, county, country, postCode,
	    	                         emailAddress, phoneNumber,
	    	                         ppsn, sscn2);

		// Commented out code: Return data from DB in JSON format - i.e. a CLOB that contains JSON data
	    /*CallableStatement cstmt = conn.prepareCall("{CALL scd_operations.view_customer_details(?,?)}");
		Clob jsonSCDDataStruct = conn.createClob();
		ObjectMapper objectMapper = new ObjectMapper();
		
		cstmt.setLong(1, Long.parseLong(sscn));
		cstmt.registerOutParameter(2, Types.CLOB);

		cstmt.executeUpdate();
		// conn.commit();
		
		Clob jsonClobStr = cstmt.getClob(2);
		long jsonClobStrLength = jsonClobStr.length();
		System.out.println(jsonClobStr.getSubString(1, (int) jsonClobStrLength));
		
		cstmt.close();
		conn.close();*/
		
	} catch (SQLException e) {
	    retCode = e.getErrorCode();
		System.err.println("Error: " + retCode + " - " + e.getMessage());
		conn.close();

		throw new RuntimeException(e);
		}
	}

	@ApiOperation(value = "View a customer's details via SSCN - versioned content negotiation", response = Customer.class)
	@RequestMapping(value = "{sscn}", method = RequestMethod.GET, produces = { "application/scd.api-v3+json" })
	@ResponseBody
	public Customer sscn2(@PathVariable(value = "sscn") String sscn) {
		return CustomerTestData.customer();
	}

	@ApiOperation(value = "Delete a customer from the SCD")
	@RequestMapping(value = "{sscn}", method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "The customer was successfully deleted")
	public void delete(@PathVariable(value = "sscn") String sscn) throws SQLException {
		int retCode;
		Connection conn = null;
    try {
		// Delete customer
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:peter/peter@localhost:1521/ORA12C");
		conn = ods.getConnection();

		CallableStatement cstmt = conn.prepareCall("{CALL scd_operations.delete_customer(?)}");

		cstmt.setLong(1, Long.parseLong(sscn));

		cstmt.executeUpdate();
		// conn.commit();
		
		cstmt.close();
		conn.close();

	} catch (SQLException e) {
	    retCode = e.getErrorCode();
		System.err.println("Error: " + retCode + " - " + e.getMessage());
		conn.close();

		throw new RuntimeException(e);
		}
	}
}