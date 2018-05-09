package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="ORA-01653: unable to extend table \"AML\".\"STATE_SAVING_CUSTOMER\" by 218 in tablespace \"AML_TABLESPACE\"")
public class WhenOthers extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public WhenOthers(String key){
		super(key+" not available");
	}
}