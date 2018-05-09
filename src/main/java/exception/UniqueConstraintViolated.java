package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY, reason="ORA-00001: unique constraint (AML.SSC_UK) violated\nOra-06512: at AML.P_PB_OPERATIONS.ADD_NEW_CUSTOMER, line 91\nORA-06512: at line 2")
public class UniqueConstraintViolated extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public UniqueConstraintViolated(String key){
		super(key+" not available");
	}
} 	