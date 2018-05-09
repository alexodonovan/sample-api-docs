package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY, reason="ORA-02990: check constraint (JSON_DS_CHK) violated: the JSON structure is invalid" + "\n" + "ORA-20700: the JSON structure is invalid")
public class InvalidJSONStructure extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public InvalidJSONStructure(String key){
		super(key+" not available");
	}
} 	