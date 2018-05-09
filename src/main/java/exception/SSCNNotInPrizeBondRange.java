package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY, reason="ORA-20701: The SSCN number (250000) supplied with the JSON data does not fall within the range of SSCNs assigned to Prize Bonds")
public class SSCNNotInPrizeBondRange extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public SSCNNotInPrizeBondRange(String key){
		super(key+" not available");
	}
} 	