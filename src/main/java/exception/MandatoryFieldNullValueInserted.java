package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="ORA-01400: cannot insert NULL into (\"AML\".\"PB_CUSTOMER\".\"ADDRESS_LINE_1\")")
public class MandatoryFieldNullValueInserted extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public MandatoryFieldNullValueInserted(String key){
		super(key+" not available");
	}
}