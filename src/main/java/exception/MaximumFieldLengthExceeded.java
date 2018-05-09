package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="ORA-01289: value too large for column\"AML\".\"AML_P_PB_OPERATIONS.ADD_CUSTOMER\".\"ADDRESS_LINE_1 (actual: 89, maximum: 32)\"")
public class MaximumFieldLengthExceeded extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public MaximumFieldLengthExceeded(String key){
		super(key+" not available");
	}
} 	